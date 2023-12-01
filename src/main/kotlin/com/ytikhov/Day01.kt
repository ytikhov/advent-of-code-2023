package com.ytikhov

class Day01 {
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
    fun part1(filename: String) {
        val data = this::class.java.classLoader.getResource(filename)?.readText()?.split("\n") ?: listOf()
        var sum = 0L
        for (line in data) {
            val left = firstDigit(line)
            val right = firstDigit(line.reversed())
            sum += (10 * left + right)
        }
        println("Day 1 part 1 result=$sum")
    }

    fun part2(filename: String) {
        val data = this::class.java.classLoader.getResource(filename)?.readText()?.split("\n") ?: listOf()
        var sum = 0L
        for (line in data) {
            var forward = line
            var pos = 0
            while (pos < forward.length) {
                for (conv in digitConvertor) {
                    if (forward.substring(pos).startsWith(conv.first)) {
                        forward = forward.substring(0, pos) + forward.substring(pos).replaceFirst(conv.first, conv.second)
                        break
                    }
                }
                pos++
            }
            pos = 0
            var backward = line.reversed()
            val backwardConvertor = digitConvertor.map { Pair(it.first.reversed(), it.second) }
            while (pos < backward.length) {
                for (conv in backwardConvertor) {
                    if (backward.substring(pos).startsWith(conv.first)) {
                        backward = backward.substring(0, pos) + backward.substring(pos).replaceFirst(conv.first, conv.second)
                        break
                    }
                }
                pos++
            }
            val left = firstDigit(forward)
            val right = firstDigit(backward)
            sum += (10 * left + right)
        }
        println("Day 1 part 2 result=$sum")
    }

    fun firstDigit(string: String): Int {
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