package aoc.year2016

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day3Test {

  @Test
  fun part1TestInput() {
    Day3.solvePart1("5 10 25") shouldBeExactly 0
    Day3.solvePart1("3 3 3") shouldBeExactly 1
  }

  @Test
  fun part1() {
    Day3.solvePart1() shouldBeExactly 1050
  }

  @Test
  fun part2TestInput() {
    Day3.solvePart2(
      """
      101 301 501
      102 302 502
      103 303 503
      201 401 601
      202 402 602
      203 403 603
      """.trimIndent(),
    ) shouldBeExactly 6
  }

  @Test
  fun part2() {
    Day3.solvePart2() shouldBeExactly 1921
  }
}
