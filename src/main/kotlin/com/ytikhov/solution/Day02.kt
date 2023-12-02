package com.ytikhov.solution

import com.ytikhov.util.FileData

class Day02(filename: String) {
    private val cubeLimits = mapOf("red" to 12, "green" to 13, "blue" to 14)

    private val data = FileData(filename).data

    fun solvePart1(): Int {
        var result = 0
        for (line in data) {
            val (gameInfo, gameResults) = line.split(":")
            val (_, gameNumber) = gameInfo.split(" ")
            val attempts = gameResults.split(";")
            var possible = true
            beginning@ for (attempt in attempts) {
                val colors = attempt.trim().split(",")
                for (color in colors) {
                    val (colorNumber, colorName) = color.trim().split(" ")
                    if (cubeLimits[colorName.trim()]!! < colorNumber.trim().toInt()) {
                        possible = false
                        break@beginning
                    }
                }
            }
            if (possible) {
                result += gameNumber.toInt()
            }
        }
        return result
    }

    fun solvePart2(): Int {
        var result = 0
        for (line in data) {
            val bag = mutableMapOf("green" to 0, "red" to 0, "blue" to 0)
            val (_, gameResults) = line.split(":")
            val attempts = gameResults.split(";")
            for (attempt in attempts) {
                val colors = attempt.trim().split(",")
                for (color in colors) {
                    val (colorNumber, colorName) = color.trim().split(" ")
                    bag[colorName] = bag[colorName]!!.coerceAtLeast(colorNumber.toInt())
                }
            }
            result += bag.values.reduce { a, b -> a * b }
        }
        return result
    }
}