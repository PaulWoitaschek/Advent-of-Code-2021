package aoc.year2021

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day2Test {
  @Test
  fun part1() {
    Day2.solvePart1() shouldBe 2102357
  }

  @Test
  fun part2() {
    Day2.solvePart2() shouldBe 2101031224
  }
}
