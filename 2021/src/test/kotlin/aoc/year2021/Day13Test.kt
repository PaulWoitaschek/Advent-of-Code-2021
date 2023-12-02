package aoc.year2021

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day13Test {

  @Test
  fun part1() {
    Day13.solvePart1() shouldBe 17
  }

  @Test
  fun part2() {
    Day13.solvePart2() shouldBe """
          ⬛⬛⬛⬛⬛
          ⬛⬜⬜⬜⬛
          ⬛⬜⬜⬜⬛
          ⬛⬜⬜⬜⬛
          ⬛⬛⬛⬛⬛
    """.trimIndent()
  }
}
