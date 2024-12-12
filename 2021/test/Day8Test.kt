package aoc.year2021

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day8Test {
  @Test
  fun part1() {
    Day8.solvePart1() shouldBe 392
  }

  @Test
  fun part2() {
    Day8.solvePart2() shouldBe 1004688
  }
}
