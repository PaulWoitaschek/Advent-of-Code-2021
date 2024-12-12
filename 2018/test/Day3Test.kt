package aoc.year2018

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day3Test {

  private val testInput = """
      #1 @ 1,3: 4x4
      #2 @ 3,1: 4x4
      #3 @ 5,5: 2x2
  """.trimIndent()

  @Test
  fun part1() {
    Day3.solvePart1() shouldBeExactly 100261
  }

  @Test
  fun part1TestInput() {
    Day3.solvePart1(testInput) shouldBeExactly 4
  }

  @Test
  fun part2() {
    Day3.solvePart2() shouldBeExactly 251
  }

  @Test
  fun part2TestInput() {
    Day3.solvePart2(testInput) shouldBeExactly 3
  }
}
