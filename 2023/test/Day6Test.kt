package aoc.year2023

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day6Test {
  private val testInput: String = """
    Time:      7  15   30
    Distance:  9  40  200
  """.trimIndent()

  @Test
  fun part1() {
    Day6.solvePart1() shouldBeExactly 220320
  }

  @Test
  fun part1TestInput() {
    Day6.solvePart1(testInput) shouldBeExactly 288
  }

  @Test
  fun part2() {
    Day6.solvePart2() shouldBeExactly 34454850
  }

  @Test
  fun part2TestInput() {
    Day6.solvePart2(testInput) shouldBeExactly 71503
  }
}
