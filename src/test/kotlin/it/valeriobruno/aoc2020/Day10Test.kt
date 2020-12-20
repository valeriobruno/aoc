package it.valeriobruno.aoc2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Test {

    val numbers1 = listOf(
        16,
        10,
        15,
        5,
        1,
        11,
        7,
        19,
        6,
        12,
        4
    )

    val numbers2 = listOf(28,
        33,
        18,
        42,
        31,
        14,
        46,
        20,
        48,
        47,
        24,
        23,
        49,
        45,
        19,
        38,
        39,
        11,
        1,
        32,
        25,
        35,
        8,
        17,
        7,
        9,
        4,
        2,
        34,
        10,
        3)

    @Test
    fun testHowManyDifferences1() {


        val graph = AdapterGraph(numbers1)
        assertEquals(7, graph.howManyDifferences(1))
        assertEquals(5, graph.howManyDifferences(3))

    }

    @Test
    fun testHowManyDifferences2()
    {

        val graph = AdapterGraph(numbers2)
        assertEquals(22, graph.howManyDifferences(1))
        assertEquals(10, graph.howManyDifferences(3))
    }

    @Test
    fun testCountPaths1()
    {
        val graph = AdapterGraph(numbers1)
        assertEquals(8L,graph.countPaths())
    }

    @Test
    fun testCountPaths2()
    {
        val graph = AdapterGraph(numbers2)
        assertEquals(19208L,graph.countPaths())
    }
}