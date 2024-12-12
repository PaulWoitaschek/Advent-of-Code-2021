package aoc.year2021

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day17Test {
  @Test
  fun part1() {
    Day17.solvePart1() shouldBe 3916
  }

  @Test
  fun part2() {
    Day17.solvePart2() shouldBe 2986
  }
}
