package aoc.year2024

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.longs.shouldBeExactly
import org.junit.jupiter.api.Test

class Day11Test {

  @Test
  fun part1TestInput() {
    Day11.solvePart1("125 17") shouldBeExactly 55312
  }

  @Test
  fun part1() {
    Day11.solvePart1() shouldBeExactly 194557
  }

  @Test
  fun part2() {
    Day11.solvePart2() shouldBeExactly 231532558973909
  }
}
