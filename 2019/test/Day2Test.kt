package aoc.year2019

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.longs.shouldBeExactly
import org.junit.jupiter.api.Test

class Day2Test {

  @Test
  fun part1() {
    Day2.solvePart1() shouldBeExactly 9581917
  }

  @Test
  fun part2() {
    Day2.solvePart2() shouldBeExactly 2505
  }
}
