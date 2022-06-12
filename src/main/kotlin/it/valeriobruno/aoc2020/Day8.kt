package it.valeriobruno.aoc2020

import java.io.File
import java.lang.RuntimeException
import java.util.regex.Pattern

class Calculator
{
    val commandReged = Pattern.compile("(jmp|nop|acc) ([\\+-]\\d+)")
    var accumulatedValue = 0
    var position = 0
    var iteration = 0
    val executedLines = mutableSetOf<Int>()

    fun acc(parameter : Int)
    {
        accumulatedValue += parameter
        position++
    }

    fun jmp(parameter: Int)
    {
        position += parameter
    }
    fun nop()
    {
        position++
    }

    fun executeCommand(cmd : String)
    {
        val matcher = commandReged.matcher(cmd)
        if( matcher.matches())
        {
            val op = matcher.group(1)
            val parameter = matcher.group(2).toInt()
            println("it: ${++iteration}, pos: $position")
            when(op)
            {
                "acc" -> acc(parameter)
                "jmp" -> jmp(parameter)
                "nop" -> nop()
            }
            //println("it: ${++iteration}, pos: $position, acc: $accumulatedValue")
        }else throw RuntimeException("Invalid command: $cmd")
    }

    fun executeProgram(program:List<String>)
    {


        do{
            if(! (position  in program.indices))
            {
                println("Terminating..\nCalculated value $accumulatedValue")
                break
            }
            if(executedLines.contains(position)) {
                println("Looping!")
                break
            }

            executedLines.add(position)
            executeCommand(program[position])

        }while(true)
        println("Executed lines: ${executedLines.sorted()}")

    }
}

fun main()
{
    val calculator = Calculator()
    val program = File("./input8.txt").readLines()
    calculator.executeProgram(program)

   val executedJumps = calculator.executedLines.filter { line -> program[line].startsWith("jmp")  }.sorted()
    val executedNops = calculator.executedLines.filter { line -> program[line].startsWith("nop")  }.sorted()

    println(executedJumps)
    println(executedNops)
}