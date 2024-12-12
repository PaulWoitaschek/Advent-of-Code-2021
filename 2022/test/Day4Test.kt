package aoc.year2022

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day4Test {
  @Test
  fun day4() {
    Day4.solvePart1() shouldBe 657
    Day4.solvePart2() shouldBe 938
  }
}
