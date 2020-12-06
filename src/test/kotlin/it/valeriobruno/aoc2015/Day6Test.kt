package it.valeriobruno.aoc2015

import kotlin.test.Test
import kotlin.test.assertEquals

public class Day6Test {

    @Test
    fun testAllLightsOn()
    {
        val board = BinaryLightBoard()
        board.parse("turn on 0,0 through 999,999")
        assertEquals(1000000,board.countLightsOn())

        board.parse("toggle 0,0 through 999,0")
        assertEquals(1000000-1000,board.countLightsOn())

        board.parse("turn off 499,499 through 500,500")
        assertEquals(1000000-1000-4,board.countLightsOn())
    }
}
