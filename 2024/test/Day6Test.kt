package aoc.year2024

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day6Test {

  private val testInput = """
      ....#.....
      .........#
      ..........
      ..#.......
      .......#..
      ..........
      .#..^.....
      ........#.
      #.........
      ......#...
  """.trimIndent()

  @Test
  fun part1TestInput() {
    Day6.solvePart1(testInput) shouldBeExactly 41
  }

  @Test
  fun part1() {
    Day6.solvePart1() shouldBeExactly 4776
  }

  @Test
  fun part2TestInput() {
    Day6.solvePart2(testInput) shouldBeExactly 6
  }

  @Test
  fun part2() {
    Day6.solvePart2() shouldBeExactly 1586
  }
}
