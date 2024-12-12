package aoc.year2022

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day24Test {

  @Test
  fun part1TestData() {
    Day24.solvePart1(
      """
#.######
#>>.<^<#
#.<..<<#
#>v.><>#
#<^v^^>#
######.#
      """.trimIndent(),
    ) shouldBe 18
  }

  @Test
  @Disabled("Too slow")
  fun part1RealData() {
    Day24.solvePart1() shouldBe 264
  }

  @Test
  fun part2TestData() {
    Day24.solvePart2(
      """
#.######
#>>.<^<#
#.<..<<#
#>v.><>#
#<^v^^>#
######.#
      """.trimIndent(),
    ) shouldBe 54
  }

  @Test
  @Disabled("Too slow")
  fun part2RealData() {
    Day24.solvePart2() shouldBe 789
  }
}
