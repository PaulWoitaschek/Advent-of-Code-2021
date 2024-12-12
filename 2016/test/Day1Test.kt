package aoc.year2016

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day1Test {

  @Test
  fun part1() {
    Day1.solvePart1() shouldBeExactly 253
  }

  @Test
  fun part1TestInput1() {
    Day1.solvePart1("R2, L3") shouldBeExactly 5
  }

  @Test
  fun part1TestInput2() {
    Day1.solvePart1("R5, L5, R5, R3") shouldBeExactly 12
  }

  @Test
  fun part2() {
    Day1.solvePart2() shouldBeExactly 126
  }

  @Test
  fun part2TestInput() {
    Day1.solvePart2("R8, R4, R4, R8") shouldBeExactly 4
  }
}
