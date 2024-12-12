package aoc.year2017

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day2Test {

  @Test
  fun part1() {
    Day2.solvePart1() shouldBeExactly 58975
  }

  @Test
  fun part1TestInput() {
    Day2.solvePart1(
      """
      5 1 9 5
      7 5 3
      2 4 6 8
      """.trimIndent(),
    ) shouldBeExactly 18
  }

  @Test
  fun part2() {
    Day2.solvePart2() shouldBeExactly 308
  }

  @Test
  fun part2TestInput() {
    Day2.solvePart2(
      """
      5 9 2 8
      9 4 7 3
      3 8 6 5
      """.trimIndent(),
    ) shouldBeExactly 9
  }
}
