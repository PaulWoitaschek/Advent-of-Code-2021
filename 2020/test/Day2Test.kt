package aoc.year2020

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day2Test {

  @Test
  fun part1TestInput() {
    Day2.solvePart1(
      """
1-3 a: abcde
1-3 b: cdefg
2-9 c: ccccccccc
      """.trimIndent(),
    ) shouldBe 2
  }

  @Test
  fun part2TestInput() {
    Day2.solvePart2(
      """
1-3 a: abcde
1-3 b: cdefg
2-9 c: ccccccccc
      """.trimIndent(),
    ) shouldBe 1
  }

  @Test
  fun part1() {
    Day2.solvePart1() shouldBe 424
  }

  @Test
  fun part2() {
    Day2.solvePart2() shouldBe 747
  }
}
