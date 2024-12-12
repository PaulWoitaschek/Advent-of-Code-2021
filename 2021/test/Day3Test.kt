package aoc.year2021

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day3Test {
  @Test
  fun part1() {
    Day3.solvePart1() shouldBe 3901196L
  }

  @Test
  fun part2() {
    Day3.solvePart2() shouldBe 4412188L
  }
}
