package aoc.year2017

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day13Test {

  private val testInput = """
      0: 3
      1: 2
      4: 4
      6: 4
  """.trimIndent()

  @Test
  fun part1TestInput() {
    Day13.solvePart1(testInput) shouldBeExactly 24
  }

  @Test
  fun part1() {
    Day13.solvePart1() shouldBeExactly 1876
  }

  @Test
  fun part2TestInput() {
    Day13.solvePart2(testInput) shouldBeExactly 10
  }

  @Test
  fun part2() {
    Day13.solvePart2() shouldBeExactly 3964778
  }
}
