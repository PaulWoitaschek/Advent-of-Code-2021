package aoc.year2020

import aoc.library.solvePart1
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day13Test {

  @Test
  fun part1() {
    Day13.solvePart1() shouldBe 161
    Day13.solvePart1(
      """
939
7,13,x,x,59,x,31,19
      """.trimIndent(),
    ) shouldBe 295
  }
}
