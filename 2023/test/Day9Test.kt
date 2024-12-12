package aoc.year2023

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.longs.shouldBeExactly
import org.junit.jupiter.api.Test

class Day9Test {

  @Test
  fun part1() {
    Day9.solvePart1() shouldBeExactly 2174807968
  }

  @Test
  fun part1TestInput() {
    Day9.solvePart1(
      """
      0 3 6 9 12 15
      1 3 6 10 15 21
      10 13 16 21 30 45
      """.trimIndent(),
    ) shouldBeExactly 114
  }

  @Test
  fun part2() {
    Day9.solvePart2() shouldBeExactly 1208
  }

  @Test
  fun part2TestInput() {
    Day9.solvePart2(
      """
            0 3 6 9 12 15
            1 3 6 10 15 21
            10 13 16 21 30 45
      """.trimIndent(),
    ) shouldBeExactly 2
  }
}
