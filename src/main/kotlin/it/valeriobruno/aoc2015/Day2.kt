package it.valeriobruno.aoc2015

import java.nio.file.Files
import java.nio.file.Paths
import kotlin.math.min

class Box {

    private var l = 0
    private var w = 0
    private var h = 0

    companion object {
        fun fromInput(input: String) : Box {
            var box = Box()

            var sizes = input.split("x")
            box.l = sizes[0].toInt()
            box.w = sizes[1].toInt()
            box.h = sizes[2].toInt()

            return box
        }
    }

    fun wrappingPaper() : Int
    {
        var side1 = l*w
        var side2 = w*h
        var side3 = h*l

        return 2*side1 + 2*side2 + 2*side3 + min(min(side1,side2),side3)
    }

    fun ribbon() :Int
    {
        var x : Int;
        var y: Int;
        if(l>w)
        {
            x = w;
            y = min(l,h);
        }
        else{
            x = l;
            y = min(w,h);
        }

        return 2*(x+y) + l*w*h
    }


}
fun main() {
    var boxesString = Files.readAllLines(Paths.get("./input2.txt"))
    var totalPaper = 0
    var totalRibbon = 0
    for(boxString in boxesString) {
        var box = Box.fromInput(boxString)

        totalPaper+=box.wrappingPaper()
        totalRibbon+=box.ribbon()
    }

    println("total paper: $totalPaper")
    println("total ribbon: $totalRibbon")
}
