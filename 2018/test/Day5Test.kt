package aoc.year2018

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day5Test {

  private val testInput: String = "dabAcCaCBAcCcaDA"

  @Test
  fun part1() {
    Day5.solvePart1() shouldBeExactly 11590
  }

  @Test
  fun part1TestInput() {
    Day5.solvePart1(testInput) shouldBeExactly 10
  }

  @Test
  fun part2() {
    Day5.solvePart2() shouldBeExactly 4504
  }

  @Test
  fun part2TestInput() {
    Day5.solvePart2(testInput) shouldBeExactly 4
  }
}
