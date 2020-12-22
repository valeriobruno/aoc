package it.valeriobruno.aoc2020

import java.io.File

class SeatMap(lines: List<String>, val checkSeatsInLine: Boolean, val busySeatsToLeave: Int) {

    var map: Array<Array<SeatSpot>>

    init {

        map = Array(lines[0].length) { x -> Array(lines.size) { y -> SeatSpot(x, y, lines[y][x]) } }
    }

    fun finalOccupiedSeats(): Int {

        var doesChange: Boolean
        var busySeats: Int
        do {
            doesChange = false

            for (x in map.indices)
                for (y in map[x].indices) {
                    val changed = map[x][y].doesChange()
                    doesChange = doesChange || changed
                }

            for (x in map.indices)
                for (y in map[x].indices)
                    map[x][y].change()

            println("$this\n")

        } while (doesChange)

        return countBusySeats()
    }

    fun countBusySeats(): Int {
        var total = 0
        for (x in map.indices)
            for (y in map[x].indices)
                if (map[x][y].status == SeatStatus.OCCUPIED)
                    total++

        return total
    }

    override fun toString(): String {
        var strb = StringBuilder()
        for (y in map[0].indices)
            for (x in map.indices) {
                if (x == 0 && y != 0)
                    strb.append("\n")
                when (map[x][y].status) {
                    SeatStatus.LIBRE -> strb.append('L')
                    SeatStatus.OCCUPIED -> strb.append('#')
                    else ->
                        strb.append('.')
                }
            }

        return strb.toString()
    }

    inner class SeatSpot(val x: Int, val y: Int, letter: Char) {
        var status: SeatStatus
        var nextStatus: SeatStatus? = null

        init {
            status = when (letter) {
                'L' -> SeatStatus.LIBRE
                '#' -> SeatStatus.OCCUPIED
                else -> SeatStatus.FLOOR
            }
        }

        fun doesChange(): Boolean {
            calculateNextStatus()
            return nextStatus != null && status != nextStatus
        }

        fun change() {
            if (nextStatus != null)
                status = nextStatus!!
        }

        private fun calculateNextStatus() {
            when (status) {
                SeatStatus.LIBRE ->
                    if (noBusySeatsAround())
                        nextStatus = SeatStatus.OCCUPIED
                SeatStatus.OCCUPIED ->
                    if (atLeastSeatsAround(busySeatsToLeave))
                        nextStatus = SeatStatus.LIBRE
            }
        }


        private fun busySeat(direction: Direction, degree: Int): Int {
            var result: Int? = null

            when (direction) {
                Direction.UP ->
                    if (y > degree - 1) {
                        if (map[x][y - degree].status == SeatStatus.OCCUPIED)
                            result = 1
                        else if (map[x][y - degree].status == SeatStatus.LIBRE)
                            result = 0
                    } else result = 0

                Direction.DOWN ->
                    if (y < map[0].size - degree) {
                        if (map[x][y + degree].status == SeatStatus.OCCUPIED)
                            result = 1
                        else if (map[x][y + degree].status == SeatStatus.LIBRE)
                            result = 0
                    } else result = 0

                Direction.LEFT -> if (x > degree - 1) {
                    if (map[x - degree][y].status == SeatStatus.OCCUPIED)
                        result = 1
                    else if (map[x - degree][y].status == SeatStatus.LIBRE)
                        result = 0
                } else result = 0

                Direction.RIGHT -> if (x < map.size - degree) {
                    if (map[x + degree][y].status == SeatStatus.OCCUPIED)
                        result = 1
                    else if (map[x + degree][y].status == SeatStatus.LIBRE)
                        result = 0
                } else result = 0

                Direction.TOP_LEFT -> if (x > degree - 1 && y > degree - 1) {
                    if (map[x - degree][y - degree].status == SeatStatus.OCCUPIED)
                        result = 1
                    else if (map[x - degree][y - degree].status == SeatStatus.LIBRE)
                        result = 0

                } else result = 0
                Direction.TOP_RIGHT -> if (x < map.size - degree && y > degree - 1) {
                    if (map[x + degree][y - degree].status == SeatStatus.OCCUPIED)
                        result = 1
                    else if (map[x + degree][y - degree].status == SeatStatus.LIBRE)
                        result = 0
                } else result = 0

                Direction.BOTTOM_LEFT -> if (x > degree - 1 && y < map[0].size - degree) {
                    if (map[x - degree][y + degree].status == SeatStatus.OCCUPIED)
                        result = 1
                    else if (map[x - degree][y + degree].status == SeatStatus.LIBRE)
                        result = 0
                } else result = 0
                Direction.BOTTOM_RIGHT -> if (x < map.size - degree && y < map[0].size - degree) {
                    if (map[x + degree][y + degree].status == SeatStatus.OCCUPIED)
                        result = 1
                    else if (map[x + degree][y + degree].status == SeatStatus.LIBRE)
                        result = 0
                } else result = 0
            }
            if (result == null && checkSeatsInLine)
                result = busySeat(direction, degree + 1)

            if (result == null)
                result = 0

            return result
        }

        private fun noBusySeatsAround(): Boolean {
            return busySeat(Direction.UP, 1) +
                    busySeat(Direction.DOWN, 1) +
                    busySeat(Direction.LEFT, 1) +
                    busySeat(Direction.RIGHT, 1) +
                    busySeat(Direction.TOP_LEFT, 1) +
                    busySeat(Direction.TOP_RIGHT, 1) +
                    busySeat(Direction.BOTTOM_LEFT, 1) +
                    busySeat(Direction.BOTTOM_RIGHT, 1) == 0
        }

        private fun atLeastSeatsAround(n: Int): Boolean {
            return busySeat(Direction.UP, 1) +
                    busySeat(Direction.DOWN, 1) +
                    busySeat(Direction.LEFT, 1) +
                    busySeat(Direction.RIGHT, 1) +
                    busySeat(Direction.TOP_LEFT, 1) +
                    busySeat(Direction.TOP_RIGHT, 1) +
                    busySeat(Direction.BOTTOM_LEFT, 1) +
                    busySeat(Direction.BOTTOM_RIGHT, 1) >= n
        }
    }

    enum class Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT
    }

    enum class SeatStatus {
        FLOOR,
        LIBRE,
        OCCUPIED
    }
}


fun main() {
    val lines = File("./input11.txt").readLines()
    val seatMap = SeatMap(lines, false, 4)
    println(seatMap.finalOccupiedSeats())
    println("-------------------------")
    val seatMap2 = SeatMap(lines, true, 5)
    println(seatMap2.finalOccupiedSeats())
}