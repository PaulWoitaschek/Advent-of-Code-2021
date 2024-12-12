package aoc.year2021

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day5Test {
  @Test
  fun part1() {
    Day5.solvePart1() shouldBe 5690L
  }

  @Test
  fun part2() {
    Day5.solvePart2() shouldBe 17741L
  }
}
