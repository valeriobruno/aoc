package it.valeriobruno.aoc2020

import java.io.File
import java.lang.RuntimeException
import java.util.regex.Pattern

class BagLink(val fromColor: String, val toColor: String, val howManyBags: Int) {
    companion object {

        const val SHINY = "shiny gold"

        private val mainPattern: Pattern =
            Pattern.compile("(.+) bags contain (((, )?(no other|(\\d+) (.+)) bags?)+)\\.")

        private val containedPattern = Pattern.compile("(\\d+) (.+) bags?")

        fun parseRule(line: String): List<BagLink> {
            var result = mutableListOf<BagLink>()
            val matcher = mainPattern.matcher(line)

            if (matcher.matches()) {
                val containerBagColor = matcher.group(1)
                val contained = matcher.group(2)

                if (contained.equals("no other bags")) {
                    //use 0 to indicate does not contain more.
                    result.add(BagLink(containerBagColor, "none", 0))
                } else {

                    for (bag in parseContained(containerBagColor, contained)) {
                        result.add(bag)
                    }
                }


            } else throw RuntimeException("UNMATCHED rule: $line")

            return result
        }

        private fun parseContained(containerColor: String, containedStr: String): List<BagLink> {
            val result = mutableListOf<BagLink>()
            var containedList = containedStr.split(",")
            for (contained in containedList) {
                var trimmed = contained.trim()

                val containedMatcher = containedPattern.matcher(trimmed)
                containedMatcher.matches()
                val howMany = containedMatcher.group(1).toInt()
                val color = containedMatcher.group(
                    2
                )
                result.add(BagLink(containerColor, color, howMany))

            }

            return result
        }

        fun directContain(color: String, links: List<BagLink>): List<String> {
            val result = mutableListOf<String>()
            for (link in links) {
                if (link.toColor == color)
                    result.add(link.fromColor)
            }
            return result
        }

        fun areDirectlyContained(containerColor : String, links:List<BagLink>) : List<BagLink>
        {
         return links.filter { link -> link.fromColor == containerColor }
        }

        fun howManyContainsShiny(color: String, links: List<BagLink>): Int {
            val reachableColors = mutableSetOf<String>()

            var oldSize = -1
            reachableColors.add(SHINY)

            do {
                oldSize = reachableColors.size

                val tempList = mutableSetOf<String>()
                for (color in reachableColors)
                    tempList.addAll(directContain(color, links))

                reachableColors.addAll(tempList)
            } while (oldSize != reachableColors.size)

            return reachableColors.size - 1
        }

        fun howManyAreContainedByShiny(color:String, links: List<BagLink>) : Long
        {
            val toCount = mutableListOf<String>()
            var total =-1L // we're not counting the shiny gold bag

            toCount.add(color)

            while(toCount.isNotEmpty())
            {
                val element = toCount.removeAt(0)
                total++
                println(element)
                for(link in areDirectlyContained(element,links))
                    for(i in 1..link.howManyBags)
                        toCount.add(link.toColor)
            }
            return total
        }

    }

}

fun main() {
    val rules =
        File("./input7.txt").readLines().map { line -> BagLink.parseRule(line) }.reduce { acc, list -> acc.plus(list) }

    println(BagLink.howManyAreContainedByShiny(BagLink.SHINY,rules))
}