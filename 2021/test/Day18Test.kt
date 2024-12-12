package aoc.year2021

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day18Test {
  @Test
  fun part1() {
    Day18.solvePart1() shouldBe 3216
  }

  @Test
  fun part2() {
    Day18.solvePart2() shouldBe 4643
  }
}
