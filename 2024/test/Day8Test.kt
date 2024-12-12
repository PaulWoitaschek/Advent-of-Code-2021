package aoc.year2024

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day8Test {

  private val testInput = """
    ............
    ........0...
    .....0......
    .......0....
    ....0.......
    ......A.....
    ............
    ............
    ........A...
    .........A..
    ............
    ............
  """.trimIndent()

  @Test
  fun part1TestInput() {
    Day8.solvePart1(testInput) shouldBeExactly 14
  }

  @Test
  fun part1() {
    Day8.solvePart1() shouldBeExactly 341
  }

  @Test
  fun part2TestInput() {
    Day8.solvePart2(testInput) shouldBeExactly 34
  }

  @Test
  fun part2() {
    Day8.solvePart2() shouldBeExactly 1134
  }
}
