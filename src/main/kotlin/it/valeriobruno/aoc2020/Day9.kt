package it.valeriobruno.aoc2020

import java.io.File
import java.util.HashSet


class Giorno9(var input: List<Long>, val PREAMBLE_SIZE: Int) {

    inner class AdjacentSum(val minPos: Int, val maxPos: Int) {
        val sum: Long

        init {
            var total = 0L
            for (i in minPos until maxPos)
                total += input[i]

            sum = total
        }
    }

    fun sumOf(minPos: Int, maxPos: Int): Long {
        var total = 0L
        for (i in minPos until maxPos) {
            total += input[i]
        }
        return total
    }

   private val searched = HashMap<Pair<Int, Int>, List<Long>>(input.size * (input.size + 1) / 2, 0.9F)

    fun findBlockThatSumTo(target: Long, minPos: Int, maxPos: Int): List<Long> {
        var result = searched[Pair(minPos, maxPos)]
        if(result ==null) {
            println("findBlockThatSumTo $minPos $maxPos")
            result = emptyList<Long>()
            val total = sumOf(minPos, maxPos)

            if (total == target)
                result = input.subList(minPos, maxPos)
            else {
                if (total > target && maxPos - minPos + 1 > 2) {
                    result = findBlockThatSumTo(target, minPos + 1, maxPos)
                    if (result.isEmpty())
                        result = findBlockThatSumTo(target, minPos, maxPos - 1)

                }
            }
            searched[Pair(minPos, maxPos)] = result
        }
        return result
    }
}

fun main() {
    val input = File("./input9.txt").readLines().map(String::toLong)
    val day9 = Giorno9(input, 25)

    //248131121 - index 590
    val block = day9.findBlockThatSumTo(248131121, 0, 589)
    println(block)
    val sorted= block.sorted()
    println("${sorted[0]} + ${sorted[sorted.size-1]} = ${sorted[0]+sorted[sorted.size-1] }")
    println("done")
}