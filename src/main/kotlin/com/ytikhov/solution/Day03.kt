package com.ytikhov.solution

import com.ytikhov.util.FileData

class Day03(filename: String) {
    private val data = FileData(filename).data

    fun solvePart1(): Int {
        var result = 0
        for (row in data.indices) {
            var number = ""
            var isPart = false
            for (col in data[row].indices) {
                val sym = data[row][col]
                if (sym in '0'..'9') {
                    isPart = isPart || isPartNumber(Pair(row, col), data)
                    number += sym
                } else {
                    if (isPart && number.isNotEmpty()) {
                        result += number.toInt()
                    }
                    isPart = false
                    number = ""
                }
            }
            if (isPart && number.isNotEmpty()) {
                result += number.toInt()
            }
        }
        return result
    }

    fun solvePart2(): Int {
        var result = 0
        for (row in data.indices) {
            for (col in data[row].indices) {
                val sym = data[row][col]
                if (sym !in '0'..'9' && sym != '.') {
                    val parts = getGearParts(Pair(row, col), data)
                    if (parts.size > 1) {
                        var ratio = 1
                        parts.forEach {
                            ratio *= it
                        }
                        result += ratio
                    }
                }
            }
        }
        return result
    }

    private fun getGearParts(pos: Pair<Int, Int>, matrix: List<String>): Collection<Int> {
        val result = mutableListOf<Int>()
        for (row in pos.first - 1..pos.first + 1) {
            val numbersInRow = mutableMapOf<Pair<Int, Int>, Int>()
            for (col in pos.second - 1..pos.second + 1) {
                val sym = getSymbol(Pair(row, col), matrix)
                if (sym != null && sym in '0'..'9') {
                    if (!numbersInRow.keys.any { it.first <= col && it.second >= col }) {
                        var number = sym.toString()
                        var beginCol = col
                        var endCol = col
                        for (pointer in col-1 downTo 0) {
                            if (matrix[row][pointer] in '0'..'9') {
                                number = matrix[row][pointer] + number
                                beginCol = pointer
                            } else {
                                break
                            }
                        }
                        for (pointer in col+1 until matrix[row].length) {
                            if (matrix[row][pointer] in '0'..'9') {
                                number += matrix[row][pointer]
                                endCol = pointer
                            } else {
                                break
                            }
                        }
                        numbersInRow[Pair(beginCol, endCol)] = number.toInt()
                    }
                }
            }
            result.addAll(numbersInRow.map { (_, v) -> v })
        }
        return result
    }

    private fun isPartNumber(base: Pair<Int, Int>, matrix: List<String>): Boolean {
        for (row in base.first - 1..base.first + 1) {
            for (col in base.second - 1..base.second + 1) {
                val sym = getSymbol(Pair(row, col), matrix)
                if (sym != null && sym !in '0'..'9' && sym != '.') {
                    return true
                }
            }
        }

        return false
    }

    private fun getSymbol(pos: Pair<Int, Int>, matrix: List<String>): Char? {
        return try {
            matrix[pos.first].substring(pos.second, pos.second + 1).toCharArray()[0]
        } catch (e: Exception) {
            null
        }
    }
}