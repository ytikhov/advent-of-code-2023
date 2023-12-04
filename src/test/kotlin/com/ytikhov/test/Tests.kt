package com.ytikhov.test

import com.ytikhov.solution.Day01
import com.ytikhov.solution.Day02
import com.ytikhov.solution.Day03
import com.ytikhov.solution.Day04
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Tests {
    @Test
    fun day01Tests() {
        val day01 = Day01("day01/day1.txt")
        Assertions.assertEquals(54159, day01.solvePart1())
        Assertions.assertEquals(53866, day01.solvePart2())
    }

    @Test
    fun day02Tests() {
        val day02 = Day02("day02/day2.txt")
        Assertions.assertEquals(2268, day02.solvePart1())
        Assertions.assertEquals(63542, day02.solvePart2())
    }

    @Test
    fun day03Tests() {
        val day03 = Day03("day03/day3.txt")
        Assertions.assertEquals(536576, day03.solvePart1())
        Assertions.assertEquals(75741499, day03.solvePart2())
    }

    @Test
    fun day04Tests() {
        val day04 = Day04("day04/day4.txt")
        Assertions.assertEquals(20855, day04.solvePart1())
        Assertions.assertEquals(5489600, day04.solvePart2())
    }
}