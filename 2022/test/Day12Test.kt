package aoc.year2022

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day12Test {

  @Test
  fun part1TestInput() {
    Day12.solvePart1(
      """
Sabqponm
abcryxxl
accszExk
acctuvwj
abdefghi
      """.trimIndent(),
    ) shouldBe 31
  }

  @Test
  fun part2TestInput() {
    Day12.solvePart2(
      """
Sabqponm
abcryxxl
accszExk
acctuvwj
abdefghi
      """.trimIndent(),
    ) shouldBe 29
  }

  @Test
  fun part1() {
    Day12.solvePart1() shouldBe 383
  }

  @Test
  fun part2() {
    Day12.solvePart2() shouldBe 377
  }
}
