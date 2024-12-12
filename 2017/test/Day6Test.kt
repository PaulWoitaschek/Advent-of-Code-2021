package aoc.year2017

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day6Test {

  @Test
  fun part1TestInput() {
    Day6.solvePart1("0\t2\t7\t0") shouldBeExactly 5
  }

  @Test
  fun part1() {
    Day6.solvePart1() shouldBeExactly 3156
  }

  @Test
  fun part2TestInput() {
    Day6.solvePart2("0\t2\t7\t0") shouldBeExactly 4
  }

  @Test
  fun part2() {
    Day6.solvePart2() shouldBeExactly 1610
  }
}
