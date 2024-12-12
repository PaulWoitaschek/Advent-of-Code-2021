package aoc.year2021

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day12Test {

  private val testInput = """
    start-A
    start-b
    A-c
    A-b
    b-d
    A-end
    b-end
  """.trimIndent()

  @Test
  fun part1() {
    Day12.solvePart1() shouldBe 5212
  }

  @Test
  fun part1TestInput() {
    Day12.solvePart1(testInput) shouldBe 10
  }

  @Test
  fun part2() {
    Day12.solvePart2() shouldBe 134862
  }

  @Test
  fun part2TestInput() {
    Day12.solvePart2(testInput) shouldBe 36
  }
}
