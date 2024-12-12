package aoc.year2017

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day12Test {

  private val testInput = """
      0 <-> 2
      1 <-> 1
      2 <-> 0, 3, 4
      3 <-> 2, 4
      4 <-> 2, 3, 6
      5 <-> 6
      6 <-> 4, 5
  """.trimIndent()

  @Test
  fun part1TestInput() {
    Day12.solvePart1(testInput) shouldBeExactly 6
  }

  @Test
  fun part1() {
    Day12.solvePart1() shouldBeExactly 134
  }

  @Test
  fun part2TestInput() {
    Day12.solvePart2(testInput) shouldBeExactly 2
  }

  @Test
  fun part2() {
    Day12.solvePart2() shouldBeExactly 193
  }
}
