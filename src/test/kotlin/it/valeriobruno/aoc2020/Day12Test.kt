package it.valeriobruno.aoc2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day12Test {

    @Test
    fun testManhattanDistance()
    {
        val navigation = listOf("F10",
        "N3",
        "F7",
        "R90",
        "F11")

        val ferry = Ferry()
        ferry.navigate(navigation)
        assertEquals(25,ferry.getManhattanDistanceFromOrigin())
    }
}