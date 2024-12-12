package aoc.year2021

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day1Test {

  private val testInput = """
    199
    200
    208
    210
    200
    207
    240
    269
    260
    263
  """.trimIndent()

  @Test
  fun part1() {
    Day1.solvePart1() shouldBe 1791
  }

  @Test
  fun part1TestInput() {
    Day1.solvePart1(testInput) shouldBe 7
  }

  @Test
  fun part2() {
    Day1.solvePart2() shouldBe 1822
  }

  @Test
  fun part2TestInput() {
    Day1.solvePart2(testInput) shouldBe 5
  }
}
