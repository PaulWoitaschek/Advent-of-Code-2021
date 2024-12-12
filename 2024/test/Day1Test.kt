package aoc.year2024

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.longs.shouldBeExactly
import org.junit.jupiter.api.Test

class Day1Test {

  @Test
  fun part1TestInput() {
    Day1.solvePart1(
      """
      3   4
      4   3
      2   5
      1   3
      3   9
      3   3
      """.trimIndent(),
    ) shouldBeExactly 11
  }

  @Test
  fun part1() {
    Day1.solvePart1() shouldBeExactly 2176849
  }

  @Test
  fun part2TestInput() {
    Day1.solvePart2(
      """
      3   4
      4   3
      2   5
      1   3
      3   9
      3   3
      """.trimIndent(),
    ) shouldBeExactly 31
  }

  @Test
  fun part2() {
    Day1.solvePart2() shouldBeExactly 23384288
  }
}
