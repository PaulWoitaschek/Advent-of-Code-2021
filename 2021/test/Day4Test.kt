package aoc.year2021

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day4Test {
  @Test
  fun part1() {
    Day4.solvePart1() shouldBe 33348
  }

  @Test
  fun part2() {
    Day4.solvePart2() shouldBe 8112
  }
}
