package aoc.year2022

import aoc.library.solvePart1
import aoc.library.solvePart1WithTestInput
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day22Test {

  @Test
  fun part1TestInput() {
    Day22.solvePart1WithTestInput() shouldBe 6032
  }

  @Test
  fun part1() {
    Day22.solvePart1() shouldBe 89224
  }
}
