package aoc.year2022

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day3Test {
  @Test
  fun day3() {
    Day3.solvePart1() shouldBe 8039
    Day3.solvePart2() shouldBe 2510
  }
}
