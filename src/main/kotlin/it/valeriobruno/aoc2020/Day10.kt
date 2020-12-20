package it.valeriobruno.aoc2020

import java.io.File
import java.util.*
import java.util.stream.Collectors

class AdapterNode(val outJoltage: Int) : Comparable<AdapterNode> {
    val pluggableAdapters = sortedSetOf<AdapterNode>()

    fun linkTo(pluggableNode: AdapterNode) {
        if (this.canInputTo(pluggableNode))
            pluggableAdapters.add(pluggableNode)
        else throw RuntimeException("Invalid connection ${this.outJoltage} <- ${pluggableNode.outJoltage}")
    }

    fun canInputTo(other: AdapterNode): Boolean {
        return this.compareTo(other) != 0 && //avoid input to itself
                other.outJoltage - this.outJoltage in 1..3
    }

    override fun compareTo(other: AdapterNode): Int {
        return this.outJoltage.compareTo(other.outJoltage)
    }

    override fun toString(): String {
        return outJoltage.toString()
    }
}

class AdapterGraph(outJoltages: List<Int>) {
    val deviceAdapter: AdapterNode
    val wallOutput : AdapterNode

    var allAdapters: TreeSet<AdapterNode>
    var pathMemory  = mutableMapOf<AdapterNode,Long>()

    init {

        allAdapters =
            outJoltages.stream().map { value -> AdapterNode(value) }.collect(Collectors.toCollection(::TreeSet))

        val deviceJoltage = outJoltages.maxOrNull()!! + 3
        wallOutput = AdapterNode(0)
        deviceAdapter = AdapterNode(deviceJoltage)

        allAdapters.add(wallOutput)
        allAdapters.add(deviceAdapter)

        //link the nodes
        for (adapterIn in allAdapters) {
            for (adapterOut in allAdapters)
                if (adapterIn.canInputTo(adapterOut))
                    adapterIn.linkTo(adapterOut)
        }
    }        //end of default constructor

    fun howManyDifferences(difference: Int): Int {
        var total = 0
        for (adapter in allAdapters) {
            /* because we are considering THE path which includes ALL the nodes,
            the next node will just be the min
             */
            val linked = adapter.pluggableAdapters.minOrNull()
            if (linked != null && linked.outJoltage - adapter.outJoltage == difference)
                total += 1
        }
        return total
    }

    fun countPaths():Long
    {
        return countPaths(wallOutput)
    }

    private fun countPaths(currentNode: AdapterNode) : Long
    {
        if(currentNode == deviceAdapter)
            return 1
        else
        {
            var total = 0L
            for(pluggableAdapter in currentNode.pluggableAdapters)
            {
                if(!pathMemory.containsKey(pluggableAdapter))
                    pathMemory[pluggableAdapter] = countPaths(pluggableAdapter)

                total += pathMemory[pluggableAdapter]!!
            }
            return total
        }
    }
}

fun main() {
    val allAdapters = mutableListOf<Int>()
    File("./input10.txt").forEachLine { line -> allAdapters.add(line.toInt()) }

    val graph = AdapterGraph(allAdapters)
    println(graph.howManyDifferences(1) * graph.howManyDifferences(3))
    println(graph.countPaths())
}