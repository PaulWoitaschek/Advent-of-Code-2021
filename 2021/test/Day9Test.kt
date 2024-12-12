package aoc.year2021

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day9Test {
  @Test
  fun part1() {
    Day9.solvePart1() shouldBe 570L
  }

  @Test
  fun part2() {
    Day9.solvePart2() shouldBe 899392L
  }
}
