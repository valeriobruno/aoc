package it.valeriobruno.aoc2020

import java.io.File
import java.lang.RuntimeException
import java.util.regex.Pattern

const val NOP = "nop"
const val JMP = "jmp"

class OpNode(val line: Int, val operation: String, val parameter: Int) {
    var prevNodes = mutableListOf<OpNode>()
}

//zero based
val allNodes = mutableMapOf<Int, OpNode>()

val commandRegex = Pattern.compile("(jmp|nop|acc) ([\\+-]\\d+)")

fun nodeBuilder(cmd: String, line: Int): OpNode {
    val matcher = commandRegex.matcher(cmd)

    if (matcher.matches()) {
        val op = matcher.group(1)
        val parameter = matcher.group(2).toInt()

        return OpNode(line, op, parameter)
    } else throw RuntimeException("Invalid command: $cmd")
}

fun buildGraph(program: List<String>) {
    val finalNode = OpNode(program.size, NOP, 0)
    allNodes[program.size] = finalNode

    program.forEachIndexed { index, cmd -> allNodes[index] = nodeBuilder(cmd, index) }

    for (i in program.indices) {
        val curNode = allNodes[i]

        if (curNode!!.operation == JMP) {
            if (allNodes[i + curNode.parameter] != null) {
                allNodes[i + curNode.parameter]!!.prevNodes.add(curNode)
            } else throw  RuntimeException("JMP ouside of program at $i")
        } else {
            allNodes[i + 1]!!.prevNodes.add(curNode)
        }
    }
}

fun findConnectedInstructions(programLen: Int): List<Int> {
    val connectedInstructions = mutableSetOf<Int>()
    var size = 0
    allNodes[programLen]!!.prevNodes.forEach { prev -> connectedInstructions.add(prev.line) }

    while (size != connectedInstructions.size) {
        size = connectedInstructions.size

       val temp = mutableSetOf<Int>()
        connectedInstructions.forEach { line ->
            allNodes[line]!!.prevNodes.forEach { prev -> temp.add(prev.line) }
        }

        connectedInstructions.addAll(temp)
    }

    return connectedInstructions.sorted()
}

fun main() {
    val program = File("./input8.txt").readLines()
    buildGraph(program)

    println(findConnectedInstructions(program.size))
}