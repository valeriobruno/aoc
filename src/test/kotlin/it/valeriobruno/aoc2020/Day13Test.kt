package it.valeriobruno.aoc2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day13Test {

    @Test
    fun test1()
    {
        val busIds = "7,13,x,x,59,x,31,19"
        val ttable = BusTimeTable(busIds)

        assertEquals(59, ttable.firstBusAfter(939))
        assertEquals(295,ttable.problem1(939))
    }

    @Test
    fun testEarliest()
    {
        val busIds = "7,13,x,x,59,x,31,19"
        val ttable = BusTimeTable(busIds)

        assertEquals(1068781, ttable.earliestTimestamp())
//3162341 mcm
        // a = 451763
    }

}