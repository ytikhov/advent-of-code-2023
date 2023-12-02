package com.ytikhov.solution

import com.ytikhov.util.FileData

class Day01(filename: String) {
    val digitConvertor = listOf(
        Pair("one", "1"),
        Pair("two", "2"),
        Pair("three", "3"),
        Pair("four", "4"),
        Pair("five", "5"),
        Pair("six", "6"),
        Pair("seven", "7"),
        Pair("eight", "8"),
        Pair("nine", "9")
    )
    private val data = FileData(filename).data

    fun solvePart1(): Long {
        var result = 0L
        for (line in data) {
            val left = firstDigit(line)
            val right = firstDigit(line.reversed())
            result += (10 * left + right)
        }
        return result
    }

    fun solvePart2(): Long {
        var result = 0L
        for (line in data) {
            val forward = convertFirstNumberOccurence(line, digitConvertor)
            val backward = convertFirstNumberOccurence(line.reversed(), digitConvertor.map { Pair(it.first.reversed(), it.second) })
            val left = firstDigit(forward)
            val right = firstDigit(backward)
            result += (10 * left + right)
        }
        return result
    }

    private fun convertFirstNumberOccurence(input: String, convertor: List<Pair<String, String>>): String {
        var pos = 0
        var convertedInput = input
        while (pos < convertedInput.length) {
            for (conv in convertor) {
                if (convertedInput.substring(pos).startsWith(conv.first)) {
                    convertedInput = convertedInput.substring(0, pos) + convertedInput.substring(pos).replaceFirst(conv.first, conv.second)
                    break
                }
            }
            pos++
        }
        return convertedInput
    }

    private fun firstDigit(string: String): Int {
        var res = 0
        for (sym in string) {
            if (sym in '0'..'9') {
                res = sym.digitToInt()
                break
            }
        }

        return res
    }
}