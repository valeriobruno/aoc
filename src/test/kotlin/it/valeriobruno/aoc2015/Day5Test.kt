package it.valeriobruno.aoc2015

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day5Test {

    @Test
    fun testNice1()
    {
        assertTrue(NiceOrNaughty("ugknbfddgicrmopn").isNice())
        assertTrue(NiceOrNaughty("aaa").isNice())
        assertFalse(NiceOrNaughty("jchzalrnumimnmhp").isNice())
        assertFalse(NiceOrNaughty("haegwjzuvuyypxyu").isNice())
        assertFalse(NiceOrNaughty("dvszwmarrgswjxmb").isNice())
    }

    @Test
    fun testNice2()
    {
        assertTrue(NiceOrNaughty("qjhvhtzxzqqjkmpb").isNice2())//true
        assertTrue(NiceOrNaughty("xxyxx").isNice2())//true
        assertFalse(NiceOrNaughty("uurcxstgmygtbstg").isNice2())//false
        assertFalse(NiceOrNaughty("ieodomkazucvgmuy").isNice2())//false
        assertFalse(NiceOrNaughty("aaaxyx").isNice2())
        assertTrue(NiceOrNaughty("aaaaxyx").isNice2())
        assertTrue(NiceOrNaughty("xyxy").isNice2())
        assertTrue(NiceOrNaughty("xyaya").isNice2())
    }
}