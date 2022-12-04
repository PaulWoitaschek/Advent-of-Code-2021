package de.woitaschek.aoc.year2022

import de.woitaschek.aoc.AocTest
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Year2022Tests : AocTest(2022) {

  @Test
  fun day1() {
    Day1.test(part1 = 65912, part2 = 195625)
  }

  @Test
  fun day2() {
    Day2.test(part1 = 12535, part2 = 15457)
  }

  @Test
  fun day3() {
    Day3.test(part1 = 8039, part2 = 2510)
  }

  @Test
  @Disabled
  fun solveTestInput() {
    Day2.solvePart1(testInput()) shouldBe 15
    Day2.solvePart2(testInput()) shouldBe 12
    println(Day2.solvePart1(input(2)))
    println(Day2.solvePart2(input(2)))
  }
}
