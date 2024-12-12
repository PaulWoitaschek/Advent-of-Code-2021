package aoc.year2022

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day9Test {

  @Test
  fun part2RealData() {
    Day9.solvePart2() shouldBe 2516
  }

  @Test
  fun part1RealData() {
    Day9.solvePart1() shouldBe 6190
  }

  @Test
  fun part1TestData() {
    Day9.solvePart1(
      """
      R 4
      U 4
      L 3
      D 1
      R 4
      D 1
      L 5
      R 2
      """.trimIndent(),
    ) shouldBe 13
  }

  @Test
  fun part2Short() {
    Day9.solvePart2(
      """
      R 4
      U 4
      L 3
      D 1
      R 4
      D 1
      L 5
      R 2
      """.trimIndent(),
    ) shouldBe 1
  }

  @Test
  fun part2Long() {
    Day9.solvePart2(
      """
      R 5
      U 8
      L 8
      D 3
      R 17
      D 10
      L 25
      U 20
      """.trimIndent(),
    ) shouldBe 36
  }
}
