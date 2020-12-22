package it.valeriobruno.aoc2020

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class Day11Test {
    val sample1 = listOf<String>(
        "L.LL.LL.LL",
        "LLLLLLL.LL",
        "L.L.L..L..",
        "LLLL.LL.LL",
        "L.LL.LL.LL",
        "L.LLLLL.LL",
        "..L.L.....",
        "LLLLLLLLLL",
        "L.LLLLLL.L",
        "L.LLLLL.LL"
    )

    @Test
    fun testOccupiedSeats() {
        val map = SeatMap(sample1,false,4)
        println("$map\n")
        assertEquals(37, map.finalOccupiedSeats())
    }

    @Test
    fun testOccupiedSeats2() {
        val map = SeatMap(sample1,true,5)
        println("$map\n")
        assertEquals(26, map.finalOccupiedSeats())
    }
}
