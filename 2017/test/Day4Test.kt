package aoc.year2017

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day4Test {

  @Test
  fun part1TestInput() {
    Day4.solvePart1(
      """
          aa bb cc dd ee
          aa bb cc dd aa
          aa bb cc dd aaa
      """.trimIndent(),
    ) shouldBeExactly 2
  }

  @Test
  fun part1() {
    Day4.solvePart1() shouldBeExactly 477
  }

  @Test
  fun part2TestInput() {
    Day4.solvePart2(
      """
        abcde fghij.
        abcde xyz ecdab
        a ab abc abd abf abj
        iiii oiii ooii oooi oooo
        oiii ioii iioi iiio
      """.trimIndent(),
    ) shouldBeExactly 3
  }

  @Test
  fun part2() {
    Day4.solvePart2() shouldBeExactly 167
  }
}
