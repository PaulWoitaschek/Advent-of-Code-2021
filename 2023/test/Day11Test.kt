package aoc.year2023

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.longs.shouldBeExactly
import org.junit.jupiter.api.Test

class Day11Test {

  private val testInput = """
      ...#......
      .......#..
      #.........
      ..........
      ......#...
      .#........
      .........#
      ..........
      .......#..
      #...#.....
  """.trimIndent()

  @Test
  fun part1() {
    Day11.solvePart1() shouldBeExactly 9563821
  }

  @Test
  fun part1TestInput() {
    Day11.solvePart1(testInput) shouldBeExactly 374
  }

  @Test
  fun part2() {
    Day11.solvePart2() shouldBeExactly 827009909817
  }

  @Test
  fun part2GalaxyExpansion10() {
    Day11.solve(testInput, 10) shouldBeExactly 1030
  }

  @Test
  fun part2GalaxyExpansion100() {
    Day11.solve(testInput, 100) shouldBeExactly 8410
  }
}
