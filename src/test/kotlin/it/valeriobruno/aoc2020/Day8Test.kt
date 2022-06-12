package it.valeriobruno.aoc2020

import kotlin.test.Test
import kotlin.test.assertEquals
class Day8Test {

    @Test
    fun testLoop()
    {
        val program = listOf<String>(
        "nop +0",
                "acc +1" ,
                "jmp +4" ,
                "acc +3" ,
                "jmp -3" ,
                "acc -99" ,
                "acc +1" ,
                "jmp -4" ,
                "acc +6")

        val calculator = Calculator()
        calculator.executeProgram(program)
    }
}