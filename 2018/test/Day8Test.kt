package aoc.year2018

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day8Test {

  private val testInput = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2"

  @Test
  fun part1TestInput() {
    Day8.solvePart1(testInput) shouldBeExactly 138
  }

  @Test
  fun part1() {
    Day8.solvePart1() shouldBeExactly 48496
  }

  @Test
  fun part2TestInput() {
    Day8.solvePart2(testInput) shouldBeExactly 66
  }

  @Test
  fun part2() {
    Day8.solvePart2() shouldBeExactly 32850
  }
}
