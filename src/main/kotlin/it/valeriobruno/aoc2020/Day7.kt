package it.valeriobruno.aoc2020

import java.io.File
import java.lang.RuntimeException
import java.util.regex.Pattern


class BagNetwork {
    inner class BagNode(val color: String) {

        val links = mutableMapOf<String, BagLink2>()

        fun setContained(color: String, howMany: Int) {
            val node = addNodeIfNotExist(color)
            links.put(color, BagLink2(node, howMany))
        }

        fun contains(color: String): Boolean {
            return links.containsKey(color)
        }

    }

    inner class BagLink2(val destination: BagNode, val howMany: Int) {

    }

    private val nodes = mutableMapOf<String, BagNode>()

    private fun addNodeIfNotExist(color: String): BagNode {
        return nodes.getOrPut(color, { -> BagNode(color) })
    }

    fun parseRule(rule: String) {
        val matcher = patternRule.matcher(rule)

        if (matcher.matches()) {
            val fromColor = matcher.group(1)
            val destStrs = matcher.group(2)

            //add node to the network
            val fromNode = addNodeIfNotExist(fromColor)

            if (destStrs == "no other bags")
                return

            for (str in destStrs.split(",")) {
                val destMatcher = patternDest.matcher(str.trim())

                if (destMatcher.matches()) {
                    val toColor = destMatcher.group(2)
                    val howMany = destMatcher.group(1).toInt()

                    //set the links
                    fromNode.setContained(toColor, howMany)
                } else throw RuntimeException("Unmatched dest: $str")
            }
        } else throw RuntimeException("Unmatched rule: $rule")
    }


    fun getBagsContaining(color: String): List<String> {
        return nodes.values.filter { node -> node.contains(color) }.map { node -> node.color }
    }


    fun howManyColorsCanContain(color: String): Int {
        val containerColors = mutableSetOf<String>()
        containerColors.addAll(getBagsContaining(color))

        var lastCount = 0

        do {
            lastCount = containerColors.size
            val tempSet = mutableSetOf<String>()
            containerColors.forEach { clr -> tempSet.addAll(getBagsContaining(clr)) }
            containerColors.addAll(tempSet)

        } while (lastCount != containerColors.size)

        return lastCount
    }

    fun howManyBagsAreContainedBy(color: String): Int {
        var counter = -1

        val bags = mutableListOf<String>()
        bags.add(color)

        while (bags.isNotEmpty()) {
            val temp = mutableListOf<String>()

            val bag = bags.removeAt(0)
            counter++
            nodes.get(bag)!!.links.forEach { (clr, link) ->
                for (i in 1..link.howMany)
                    bags.add(clr)
            }
        }
        return counter
    }


    companion object {
        val patternRule = Pattern.compile("(.+) bags contain (.+)\\.")
        val patternDest = Pattern.compile("(\\d+) (.+) bags?")
        const val SHINY_GOLD = "shiny gold"
    }
}

fun main() {
    val net = BagNetwork()
    File("./input7.txt").forEachLine { line -> net.parseRule(line) }

    println(net.howManyColorsCanContain(BagNetwork.SHINY_GOLD))
    println(net.howManyBagsAreContainedBy(BagNetwork.SHINY_GOLD))
}