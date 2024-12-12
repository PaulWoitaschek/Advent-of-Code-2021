package aoc.year2017

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day11Test {

  @Test
  fun part1TestInput() {
    Day11.solvePart1("ne,ne,ne") shouldBeExactly 3
    Day11.solvePart1("ne,ne,sw,sw") shouldBeExactly 0
    Day11.solvePart1("ne,ne,s,s") shouldBeExactly 2
    Day11.solvePart1("se,sw,se,sw,sw") shouldBeExactly 3
  }

  @Test
  fun part1() {
    Day11.solvePart1() shouldBeExactly 720
  }

  @Test
  fun part2() {
    Day11.solvePart2() shouldBeExactly 1485
  }
}
