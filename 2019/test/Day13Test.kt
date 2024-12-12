package aoc.year2019

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day13Test {

  @Test
  fun part1() {
    Day13.solvePart1() shouldBeExactly 361
  }

  @Test
  fun part2() {
    Day13.solvePart2() shouldBeExactly 17590
  }
}
