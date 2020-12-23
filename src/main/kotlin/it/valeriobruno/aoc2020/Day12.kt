package it.valeriobruno.aoc2020

import java.io.File
import kotlin.math.absoluteValue

class Ferry {

    var east_west = 0
    var north_south = 0
    var pointedDirection = NavigationDirections.EAST

    fun navigate(directions: List<String>) {
        directions.forEach(::navigate)
    }

    fun navigate(direction: String) {
        val action = direction[0]
        val length = direction.substring(1, direction.length).toInt()

        when (action) {
            'N' -> north(length)
            'S' -> south(length)
            'E' -> east(length)
            'W' -> west(length)
            'L' -> left(length)
            'R' -> right(length)
            'F' -> forward(length)
        }
    }

    fun north(length: Int) {
        north_south -= length
    }

    fun south(length: Int) {
        north_south += length
    }

    fun east(length: Int) {
        east_west -= length
    }

    fun west(length: Int) {
        east_west += length
    }

    fun right(degree: Int) {
        if (degree == 0)
            return

        when (this.pointedDirection) {
            NavigationDirections.NORTH -> pointedDirection = NavigationDirections.EAST
            NavigationDirections.SOUTH -> pointedDirection = NavigationDirections.WEST
            NavigationDirections.EAST -> pointedDirection = NavigationDirections.SOUTH
            NavigationDirections.WEST -> pointedDirection = NavigationDirections.NORTH
        }
        right(degree - 90)
    }

    fun left(degree: Int) {
        if (degree == 0)
            return

        when(this.pointedDirection) {
            NavigationDirections.NORTH -> pointedDirection = NavigationDirections.WEST
            NavigationDirections.SOUTH -> pointedDirection = NavigationDirections.EAST
            NavigationDirections.EAST -> pointedDirection = NavigationDirections.NORTH
            NavigationDirections.WEST -> pointedDirection = NavigationDirections.SOUTH
        }
        left(degree - 90)
    }

    fun forward(length: Int) {
        when (pointedDirection) {
            NavigationDirections.NORTH -> north(length)
            NavigationDirections.SOUTH -> south(length)
            NavigationDirections.EAST -> east(length)
            NavigationDirections.WEST -> west(length)
        }
    }


    fun getManhattanDistanceFromOrigin(): Int {
        return north_south.absoluteValue + east_west.absoluteValue
    }
}

enum class NavigationDirections {
    NORTH, SOUTH, EAST, WEST
}



fun main() {
    val ferry = Ferry()
    File("./input12.txt").forEachLine { line -> ferry.navigate(line) }
    println(ferry.getManhattanDistanceFromOrigin())
}