package aoc.year2023

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day1Test {

  @Test
  fun part1Test() {
    Day1.solvePart1(
      """
      1abc2
      pqr3stu8vwx
      a1b2c3d4e5f
      treb7uchet
      """.trimIndent(),
    ) shouldBeExactly 142
  }

  @Test
  fun part1() {
    Day1.solvePart1() shouldBe 56042
  }

  @Test
  fun part2Test() {
    Day1.solvePart2(
      """
      two1nine
      eightwothree
      abcone2threexyz
      xtwone3four
      4nineeightseven2
      zoneight234
      7pqrstsixteen
      """.trimIndent(),
    ) shouldBeExactly 281
  }

  @Test
  fun part2() {
    Day1.solvePart2() shouldBe 55358
  }
}
