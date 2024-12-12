package aoc.year2023

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day13Test {

  private val testInput: String = """
    #.##..##.
    ..#.##.#.
    ##......#
    ##......#
    ..#.##.#.
    ..##..##.
    #.#.##.#.

    #...##..#
    #....#..#
    ..##..###
    #####.##.
    #####.##.
    ..##..###
    #....#..#
  """.trimIndent()

  @Test
  fun part1() {
    Day13.solvePart1() shouldBeExactly 33975
  }

  @Test
  fun part1TestInput() {
    Day13.solvePart1(testInput) shouldBeExactly 405
  }

  @Test
  fun part2() {
    Day13.solvePart2() shouldBeExactly 29083
  }

  @Test
  fun part2Problematic() {
    Day13.solvePart2(
      """
      #...##...#..#.#..
      #...##...#..#.#..
      .#.#...#.#....#..
      #.#..#.##..##.#.#
      #..###.#..#..#...
      ###.#####.##.#.#.
      ##############...
      #.....#######..#.
      ..##..##.####..##
      #.####..##.#..#.#
      #.####..##.#..#.#
      ..##..##.####..##
      #.....#######..#.
      ##############..#
      ###.#####.##.#.#.
      #..###.#..#..#...
      #.#..#.##..##.#.#
      """.trimIndent(),
    ) shouldBeExactly 1000
  }

  @Test
  fun part2TestInput() {
    Day13.solvePart2(testInput) shouldBeExactly 400
  }
}
