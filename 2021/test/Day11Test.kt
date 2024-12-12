package aoc.year2021

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day11Test {

  private val testInput = """
    5483143223
    2745854711
    5264556173
    6141336146
    6357385478
    4167524645
    2176841721
    6882881134
    4846848554
    5283751526
  """.trimIndent()

  @Test
  fun part1() {
    Day11.solvePart1() shouldBe 1594
  }

  @Test
  fun part1TestInput() {
    Day11.solvePart1(testInput) shouldBe 1656
  }

  @Test
  fun part2() {
    Day11.solvePart2() shouldBe 437
  }

  @Test
  fun part2TestInput() {
    Day11.solvePart2(testInput) shouldBe 195
  }
}
