package it.valeriobruno.aoc2015

import java.io.File
import java.util.regex.Pattern

class BinaryLightBoard {
    private var board = Array<Array<Boolean>>(1000) { pos -> Array<Boolean>(1000) { pos -> false } }

    fun turnOn(sx: Int, sy: Int, ex: Int, ey: Int) {
        for (x in sx..ex)
            for (y in sy..ey)
                board[x][y] = true
    }

    fun turnOff(sx: Int, sy: Int, ex: Int, ey: Int) {
        for (x in sx..ex)
            for (y in sy..ey)
                board[x][y] = false
    }

    fun toggle(sx: Int, sy: Int, ex: Int, ey: Int) {
        for (x in sx..ex)
            for (y in sy..ey)
                board[x][y] = !board[x][y]
    }

    fun parse(command: String) {
        val commandPattern = Pattern.compile("(turn off|turn on|toggle) (\\d+),(\\d+) through (\\d+),(\\d+)")
        val matcher = commandPattern.matcher(command)
        matcher.matches()
        var op = matcher.group(1)
        var sx = matcher.group(2).toInt()
        var sy = matcher.group(3).toInt()

        var ex = matcher.group(4).toInt()
        var ey = matcher.group(5).toInt()

        when (op) {
            "turn off" -> this.turnOff(sx, sy, ex, ey)
            "turn on" -> this.turnOn(sx, sy, ex, ey)
            "toggle" -> this.toggle(sx, sy, ex, ey)
        }
    }

    fun countLightsOn(): Int {
        var counter = 0
        for (x in 0..999)
            for (y in 0..999)
                if (board[x][y] )
                    counter++
        return counter
    }


}

class NumericLightBoard {
    private var board = Array<Array<Int>>(1000) { pos -> Array<Int>(1000) { pos -> 0 } }

    fun turnOn(sx: Int, sy: Int, ex: Int, ey: Int) {
        for (x in sx..ex)
            for (y in sy..ey)
                board[x][y] += 1
    }

    fun turnOff(sx: Int, sy: Int, ex: Int, ey: Int) {
        for (x in sx..ex)
            for (y in sy..ey) {
                board[x][y] -= 1
                if(board[x][y]<0)
                    board[x][y] = 0
            }
    }

    fun toggle(sx: Int, sy: Int, ex: Int, ey: Int) {
        for (x in sx..ex)
            for (y in sy..ey)
                board[x][y] += 2
    }

    fun parse(command: String) {
        val commandPattern = Pattern.compile("(turn off|turn on|toggle) (\\d+),(\\d+) through (\\d+),(\\d+)")
        val matcher = commandPattern.matcher(command)
        matcher.matches()
        var op = matcher.group(1)
        var sx = matcher.group(2).toInt()
        var sy = matcher.group(3).toInt()

        var ex = matcher.group(4).toInt()
        var ey = matcher.group(5).toInt()

        when (op) {
            "turn off" -> this.turnOff(sx, sy, ex, ey)
            "turn on" -> this.turnOn(sx, sy, ex, ey)
            "toggle" -> this.toggle(sx, sy, ex, ey)
        }
    }

    fun countLightsOn(): Int {
        var counter = 0
        for (x in 0..999)
            for (y in 0..999)
                    counter+= board[x][y]
        return counter
    }


}
fun main() {
   val board = NumericLightBoard()
File("./input6.txt").forEachLine { line ->  board.parse(line) }
println(board.countLightsOn())
}