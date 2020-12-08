package it.valeriobruno.aoc2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day7Test {

    @Test
    fun testHowManyColorsCanContain()
    {
        var rules = listOf(
            "light red bags contain 1 bright white bag, 2 muted yellow bags.",
            "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
            "bright white bags contain 1 shiny gold bag.",
            "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
            "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
            "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
            "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
            "faded blue bags contain no other bags.",
            "dotted black bags contain no other bags.")

        val net = BagNetwork()

        rules.forEach(net::parseRule)
        assertEquals(4,net.howManyColorsCanContain(BagNetwork.SHINY_GOLD))
        assertEquals(32,net.howManyBagsAreContainedBy(BagNetwork.SHINY_GOLD))
    }

}