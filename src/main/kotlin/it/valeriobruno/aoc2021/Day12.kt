package it.valeriobruno.aoc2021

import java.io.File

class Node(val name: String) {
    override fun hashCode(): Int {
        return name.hashCode()
    }

    override fun toString(): String {
        return name
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Node

        if (name != other.name) return false

        return true
    }

    fun isMultiTraverse(): Boolean {
        return name[0].isUpperCase()
    }
}

class Graph() {

    private val links: MutableMap<Node, MutableList<Node>> = mutableMapOf()

    fun addLink(link: String) {
        val fromTo = link.split("-")
        val from = fromTo[0]
        val to = fromTo[1]

        link(from, to)
        link(to, from)

    }

    fun parseGraph(file: File)
    {
        file.forEachLine { line -> addLink(line) }
    }

    private fun link(from: String, to: String) {
        if (links[Node(from)] == null)
            links[Node(from)] = mutableListOf()

        links[Node(from)]?.add(Node(to))
    }

    fun countPaths(): Int {
        var lastPop: Node? = null
        var currentPath = mutableListOf<Node>()
        var nextNodes = mutableListOf<Node>(Node("start")) //Stack!
        var nrPaths = 0
        do {

            val topNode = nextNodes.removeLast()!!
            lastPop = topNode

            currentPath.add(topNode)

            val links = getReachableNodes(topNode).filter { node ->
                (node.isMultiTraverse() || !currentPath.contains(node)) && !node.equals(lastPop)
            }

            if (currentPath.size >= 2 && currentPath.first().equals(Node("start")) && currentPath.last()
                    .equals(Node("end"))
            ) {
                println("Found a path: $currentPath")
                nrPaths++
            }
            if (links.isEmpty()) {
                //nextNodes.removeLast()
                currentPath.removeLast()

            } else nextNodes.addAll(links)

        } while (nextNodes.isNotEmpty())
        return nrPaths
    }

    private fun getReachableNodes(node: Node): List<Node> {
        if (node.equals(Node("end")))
            return listOf()
        return links[node] ?: listOf()
    }
}