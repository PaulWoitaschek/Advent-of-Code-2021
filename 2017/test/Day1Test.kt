package aoc.year2017

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day1Test {

  @Test
  fun part1() {
    Day1.solvePart1() shouldBeExactly 1049
  }

  @Test
  fun part1TestInput() {
    Day1.solvePart1("1122") shouldBeExactly 3
    Day1.solvePart1("1111") shouldBeExactly 4
  }

  @Test
  fun part2() {
    Day1.solvePart2() shouldBeExactly 1508
  }

  @Test
  fun part2TestInput() {
    Day1.solvePart2("123425") shouldBeExactly 4
    Day1.solvePart2("123123") shouldBeExactly 12
  }
}
