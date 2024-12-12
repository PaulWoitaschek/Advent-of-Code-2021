package aoc.year2024

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day2Test {

  private val testInput = """
      7 6 4 2 1
      1 2 7 8 9
      9 7 6 2 1
      1 3 2 4 5
      8 6 4 4 1
      1 3 6 7 9
  """.trimIndent()

  @Test
  fun part1TestInput() {
    Day2.solvePart1(testInput) shouldBeExactly 2
  }

  @Test
  fun part1() {
    Day2.solvePart1() shouldBeExactly 341
  }

  @Test
  fun part2TestInput() {
    Day2.solvePart2(testInput) shouldBeExactly 4
  }

  @Test
  fun part2() {
    Day2.solvePart2() shouldBeExactly 404
  }
}
