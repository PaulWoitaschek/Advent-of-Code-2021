package aoc.year2017

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day17Test {

  @Test
  fun part1TestInput() {
    Day17.solvePart1("3") shouldBeExactly 638
  }

  @Test
  fun part1() {
    Day17.solvePart1() shouldBeExactly 417
  }

  @Test
  fun part2() {
    Day17.solvePart2() shouldBeExactly 34334221
  }
}
