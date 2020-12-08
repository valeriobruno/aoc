package it.valeriobruno.aoc2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day7Test {
    @Test
    fun testParser() {
        var link = BagLink.parseRule("faded blue bags contain no other bags.")
        assertEquals("faded blue", link[0].fromColor)
        assertEquals(0, link[0].howManyBags)

        link = BagLink.parseRule("dotted black bags contain no other bags.")
        assertEquals("dotted black", link[0].fromColor)
        assertEquals(0, link[0].howManyBags)

        link = BagLink.parseRule("light red bags contain 1 bright white bag, 2 muted yellow bags.")
        assertEquals("light red", link[0].fromColor)
        assertEquals("bright white", link[0].toColor)
        assertEquals(1, link[0].howManyBags)

        assertEquals("light red", link[1].fromColor)
        assertEquals("muted yellow", link[1].toColor)
        assertEquals(2, link[1].howManyBags)

    }

    @Test
    fun countHowManyContainedByShiny() {
        var rules = listOf(
            "light red bags contain 1 bright white bag, 2 muted yellow bags.",
            "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
            "bright white bags contain 1 shiny gold bag.",
            "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
            "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
            "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
            "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
            "faded blue bags contain no other bags.",
            "dotted black bags contain no other bags."
        )

        var links = rules.map { rule -> BagLink.parseRule(rule) }.reduce { acc, list -> acc.plus(list) }

        assertEquals(32, BagLink.howManyAreContainedByShiny(BagLink.SHINY, links))
    }

}