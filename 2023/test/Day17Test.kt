package aoc.year2023

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day17Test {

  private val testInput = """
      2413432311323
      3215453535623
      3255245654254
      3446585845452
      4546657867536
      1438598798454
      4457876987766
      3637877979653
      4654967986887
      4564679986453
      1224686865563
      2546548887735
      4322674655533
  """.trimIndent()

  @Test
  fun part1TestInput() {
    Day17.solvePart1(testInput) shouldBeExactly 102
  }

  @Test
  fun part1() {
    Day17.solvePart1() shouldBeExactly 698
  }

  @Test
  fun part2TestInput() {
    Day17.solvePart2(testInput) shouldBeExactly 94
  }

  @Test
  fun part2TestInput2() {
    Day17.solvePart2(
      """
      111111111111
      999999999991
      999999999991
      999999999991
      999999999991
      """.trimIndent(),
    ) shouldBeExactly 71
  }

  @Test
  fun part2() {
    Day17.solvePart2() shouldBeExactly 825
  }
}
