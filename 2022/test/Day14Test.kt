package aoc.year2022

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day14Test {

  private val testInput = """
    498,4 -> 498,6 -> 496,6
    503,4 -> 502,4 -> 502,9 -> 494,9
  """.trimIndent()

  @Test
  fun part1TestInput() {
    Day14.solvePart1(testInput) shouldBe 24
  }

  @Test
  fun part2TestInput() {
    Day14.solvePart2(testInput) shouldBe 93
  }

  @Test
  fun part1() {
    Day14.solvePart1() shouldBe 901
  }

  @Test
  fun part2() {
    Day14.solvePart2() shouldBe 24589
  }
}
