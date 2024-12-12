package aoc.year2023

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day14Test {

  private val testInput = """
            O....#....
            O.OO#....#
            .....##...
            OO.#O....O
            .O.....O#.
            O.#..O.#.#
            ..O..#O..O
            .......O..
            #....###..
            #OO..#....
  """.trimIndent()

  @Test
  fun part1TestInput() {
    Day14.solvePart1(testInput) shouldBeExactly 136
  }

  @Test
  fun part1() {
    Day14.solvePart1() shouldBeExactly 109665
  }

  @Test
  fun part2TestInput() {
    Day14.solvePart2(testInput) shouldBeExactly 64
  }

  @Test
  fun part2() {
    Day14.solvePart2() shouldBeExactly 96061
  }
}
