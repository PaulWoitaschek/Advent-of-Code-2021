package aoc.year2015

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day8Test {

  @Test
  fun part1() {
    Day8.solvePart1() shouldBeExactly 1350
  }

  @Test
  fun part1Test() {
    Day8.solvePart1(
      """
      ""
      "abc"
      "aaa\"aaa"
      "\x27"
      """.trimIndent(),
    ) shouldBeExactly 12
  }

  @Test
  fun memoryCount1() {
    Day8.memoryCount(
      """
      ""
      """.trimIndent(),
    ) shouldBeExactly 0
  }

  @Test
  fun memoryCount2() {
    Day8.memoryCount(
      """
      "abc"
      """.trimIndent(),
    ) shouldBeExactly 3
  }

  @Test
  fun memoryCount3() {
    Day8.memoryCount(
      """
      "aaa\"aaa"
      """.trimIndent(),
    ) shouldBeExactly 7
  }

  @Test
  fun memoryCount4() {
    Day8.memoryCount(
      """
      "\x27"
      """.trimIndent(),
    ) shouldBeExactly 1
  }

  @Test
  fun encodedSize1() {
    Day8.encodedSize(
      """
      ""
      """.trimIndent(),
    ) shouldBeExactly 6
  }

  @Test
  fun encodedSize2() {
    Day8.encodedSize(
      """
      "abc"
      """.trimIndent(),
    ) shouldBeExactly 9
  }

  @Test
  fun encodedSize3() {
    Day8.encodedSize(
      """
      "aaa\"aaa"
      """.trimIndent(),
    ) shouldBeExactly 16
  }

  @Test
  fun encodedSize4() {
    Day8.encodedSize(
      """
      "\x27"
      """.trimIndent(),
    ) shouldBeExactly 11
  }

  @Test
  fun part2Test() {
    Day8.solvePart2(
      """
      ""
      "abc"
      "aaa\"aaa"
      "\x27"
      """.trimIndent(),
    ) shouldBeExactly 19
  }

  @Test
  fun part2() {
    Day8.solvePart2() shouldBeExactly 2085
  }
}
