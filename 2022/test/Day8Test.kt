package aoc.year2022

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day8Test {

  @Test
  fun part1TestInput() {
    Day8.solvePart1(
      """
30373
25512
65332
33549
35390
      """.trimIndent(),
    ) shouldBe 21
  }

  @Test
  fun part2TestInput() {
    Day8.solvePart2(
      """
30373
25512
65332
33549
35390
      """.trimIndent(),
    ) shouldBe 8
  }

  @Test
  fun part1() {
    Day8.solvePart1() shouldBe 1672
  }

  @Test
  fun part2() {
    Day8.solvePart2() shouldBe 327180
  }
}
