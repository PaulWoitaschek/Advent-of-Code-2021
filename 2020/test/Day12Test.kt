package aoc.year2020

import aoc.library.solvePart1
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day12Test {

  @Test
  fun part1() {
    Day12.solvePart1() shouldBe 1148
    Day12.solvePart1(
      """
F10
N3
F7
R90
F11
      """.trimIndent(),
    ) shouldBe 25
  }
}
