package aoc.year2018

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day1Test {

  @Test
  fun part1() {
    Day1.solvePart1() shouldBeExactly 569
  }

  @Test
  fun part2() {
    Day1.solvePart2() shouldBeExactly 77666
  }

  @Test
  fun part2Test() {
    Day1.solvePart2(
      """
      +7
      +7
      -2
      -7
      -4
      """.trimIndent(),
    ) shouldBeExactly 14
  }
}
