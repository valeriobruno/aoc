package it.valeriobruno.aoc2015

import java.nio.file.Files
import java.nio.file.Paths

fun main() {
    println("Hello World!")

    var counter = 0
    var parentheses = Files.readAllLines(Paths.get("./input1.txt")).get(0)
    for (i in 0..parentheses.length-1) {
        if (parentheses[i] == '(')
            counter++
        else counter--

        if(counter == -1)
        println("At basement, position $i")
    }

    println(counter)
}