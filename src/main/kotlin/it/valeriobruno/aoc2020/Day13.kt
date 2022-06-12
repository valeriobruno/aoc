package it.valeriobruno.aoc2020

import java.io.File

class BusTimeTable(str: String) {
    val busIds: MutableSet<Int>

    val offsets: Map<Int, Int> //busId -> offset

    init {
        busIds = mutableSetOf()
        offsets = HashMap()

        str.split(",").forEachIndexed { i, s ->
            if (s != "x") {
                busIds.add(s.toInt());
                offsets[s.toInt()] = i
            }
        }
    }

    fun firstBusAfter(time: Int): Int {
        return busIds.minByOrNull { id -> timeToWaitBeforeNextRun(id, time) }!!
    }

    companion object {
        fun timeToWaitBeforeNextRun(id: Int, timeAtStop: Int): Int {
            return id - (timeAtStop % id)
        }
    }

    fun problem1(time: Int): Int {
        val busId = firstBusAfter(time)
        return busId * timeToWaitBeforeNextRun(busId, time)
    }

    fun isEarliestTimestamp(time: Long): Boolean {
        return offsets.all { entry ->
            (time + entry.value.toLong()) % entry.key.toLong() == 0L
        }
    }

    fun earliestTimestamp(): Long {
        val firstBus = busIds.first()
        var number = 2574000000000

        while (!isEarliestTimestamp(number)) {
            number += firstBus

            if (number % 1000000000 == 0L)
                println(number)
        }

        return number
    }
}

fun main() {
    val input = File("./input13.txt").readLines()

    val timeAtStop = input[0].toInt()
    val timeTable = BusTimeTable(input[1])
    println(timeTable.problem1(timeAtStop))
    println(timeTable.earliestTimestamp())
}

