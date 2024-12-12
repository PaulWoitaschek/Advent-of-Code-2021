package aoc.year2017

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day15Test {

  @Test
  fun part1TestInput() {
    Day15.solvePart1(
      """
        Generator A starts with 65
        Generator B starts with 8921
      """.trimIndent(),
    ) shouldBeExactly 588
  }

  @Test
  fun part1() {
    Day15.solvePart1() shouldBeExactly 650
  }

  @Test
  fun part2TestInput() {
    Day15.solvePart2(
      """
        Generator A starts with 65
        Generator B starts with 8921
      """.trimIndent(),
    ) shouldBeExactly 309
  }

  @Test
  fun part2() {
    Day15.solvePart2() shouldBeExactly 336
  }
}
