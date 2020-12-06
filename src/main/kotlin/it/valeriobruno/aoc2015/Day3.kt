package it.valeriobruno.aoc2015

import java.io.File
import kotlin.math.pow

class Position(var x: Int, var y: Int) {
    constructor(): this(0,0)

    override fun equals(other: Any?): Boolean {
        if (other is Position) {
            return (other).x == this.x && (other).y == this.y
        }
        return false
    }

    override fun hashCode(): Int {
        return 41 * x + (41.0.pow(2.0) * y).toInt()
    }

    fun up(): Position {
        return Position(x,y-1)
    }

    fun down() : Position{
        return Position(x,y+1)
    }

    fun left(): Position {
        return Position(x-1,y)
    }

    fun right() : Position {
        return Position(x+1,y)
    }
}

fun main() {
    var santaPosition = Position()
    var robotPosition = Position()
    var santaTurn = true

    val allHouses = mutableSetOf<Position>()
    allHouses.add(santaPosition)

    val inputReader = File("./input3.txt").bufferedReader()
    while(inputReader.ready())
    {
        if(santaTurn) {
            when (inputReader.read().toChar()) {
                '>' -> santaPosition = santaPosition.right()
                '^' -> santaPosition = santaPosition.up()
                '<' -> santaPosition = santaPosition.left()
                'v' -> santaPosition = santaPosition.down()
            }
            allHouses.add(santaPosition)
            santaTurn = false
        }else{
            when (inputReader.read().toChar()) {
                '>' -> robotPosition = robotPosition.right()
                '^' -> robotPosition = robotPosition.up()
                '<' -> robotPosition = robotPosition.left()
                'v' -> robotPosition = robotPosition.down()
            }
            allHouses.add(robotPosition)
            santaTurn = true
        }

    }
    println("Nr of houses visited: ${allHouses.size}")
}
