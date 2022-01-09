package it.valeriobruno.aoc2021

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class Day12Test {


    @Test
    fun test1()
    {
        val instance = Graph()
        instance.addLink("start-A")
        instance.addLink("start-b")
        instance.addLink("A-c")
        instance.addLink("A-b")
        instance.addLink("b-d")
        instance.addLink("A-end")
        instance.addLink("b-end")

        assertEquals(4, instance.countPaths())

    }
}