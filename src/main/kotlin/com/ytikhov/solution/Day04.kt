package com.ytikhov.solution

import com.ytikhov.util.FileData

class Day04(filename: String) {
    private val data = FileData(filename).data

    fun solvePart1(): Int {
        var result = 0
        for (line in data) {
            val (_, numbers) = line.split(":")
            val (winning, mine) = numbers.split("|")
            val winningSet = winning.trim().split(" ").map { it.trim() }.filterNot { it.isBlank() }.map { it.toInt() }.toSet()
            val mineSet = mine.trim().split(" ").map { it.trim() }.filterNot { it.isBlank() }.map { it.toInt() }.toSet()
            val remSet = winningSet subtract mineSet
            val num = winningSet.size - remSet.size
            if (num > 0) {
                result += 1 shl (num -1)
            }
        }
        return result
    }

    fun solvePart2(): Int {
        val map = mutableMapOf<Int, Int>()
        for (line in data) {
            val (card, numbers) = line.split(":")
            val cardNum = card.replaceFirst("Card","").trim().toInt()
            val (winning, mine) = numbers.split("|")
            val winningSet = winning.trim().split(" ").map { it.trim() }.filterNot { it.isBlank() }.map { it.toInt() }.toSet()
            val mineSet = mine.trim().split(" ").map { it.trim() }.filterNot { it.isBlank() }.map { it.toInt() }.toSet()
            val remSet = winningSet subtract mineSet
            val num = winningSet.size - remSet.size
            map[cardNum] = (map[cardNum] ?: 0) + 1
            if (num > 0) {
                val sum = map[cardNum] ?: 0
                for (curCard in (cardNum+1)..(cardNum+num)) {
                    map[curCard] = (map[curCard] ?: 0) + sum
                }
            }
        }
        return map.values.sum()
    }

}