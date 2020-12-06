package it.valeriobruno.aoc2015

import java.io.File

class NiceOrNaughty(var word: String) {
    fun hasRepetition(): Boolean {
        var result = false

        for (c in 0..word.length - 2) {
            if (word[c] == word[c + 1]) {
                result = true
                break
            }
        }
        return result
    }

    fun hasThreeVowels(): Boolean {
        var result = false
        var counter = 0

        for (c in 0..word.length - 1) {
            if (word[c] in setOf<Char>('a', 'e', 'i', 'o', 'u'))
                counter++
            if (counter == 3) {
                result = true
                break
            }
        }
        return result
    }

    fun hasBadLetters(): Boolean {
        return word.contains("ab")
                || word.contains("cd")
                || word.contains("pq")
                || word.contains("xy")
    }

    fun isNice(): Boolean {
        return hasRepetition() && hasThreeVowels() && !hasBadLetters()
    }

    fun hasInterleavedRepeat(): Boolean {
        var result = false

        for (c in 0..word.length - 3) {
            if (word[c] == word[c + 2] && word[c] != word[c + 1]) {
                result = true
                print("$word -> ${word[c]}${word[c + 1]}${word[c + 2]} ")
                break
            }
        }
        if(result == false)
            println("$word -> no same letters interleaved")
        return result
    }

    fun hasDoubleRepetition(): Boolean {
        var result = false

        for (c in 0..word.length - 2 step 2) {
            if (word.indexOf("${word[c]}${word[c + 1]}", c + 2) > 0) {
                result = true
                println("${word[c]}${word[c + 1]}")
                break
            }
        }
        if(result == false)
            println("no repeated pair")
        return result
    }

    fun isNice2(): Boolean {
        return hasInterleavedRepeat() && hasDoubleRepetition()
    }
}

fun main() {
    val niceStrings = File("./input5.txt").readLines().filter { line -> NiceOrNaughty(line).isNice2() }
//useLines {  }forEachLine { line -> NiceOrNaughty(line) }
    println("nice ones: ${niceStrings.size}")



    println("Nice part2")



    println(NiceOrNaughty("xkriqxkrprjwpncs").isNice2())
    println("nice ones: ${niceStrings}")
}