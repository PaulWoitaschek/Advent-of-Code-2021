package aoc.year2021

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day6Test {

  @Test
  fun part1() {
    Day6.solvePart1() shouldBe 387413
  }

  @Test
  fun part2() {
    Day6.solvePart2() shouldBe 1738377086345
  }
}
