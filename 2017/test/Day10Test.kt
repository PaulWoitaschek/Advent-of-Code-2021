package aoc.year2017

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day10Test {

  @Test
  fun part1TestInput() {
    Day10.solvePart1("3,4,1,5", 5) shouldBeExactly 12
  }

  @Test
  fun part1() {
    Day10.solvePart1() shouldBeExactly 19591
  }

  @Test
  fun part2TestInput() {
    Day10.solvePart2("1,2,3") shouldBe "3efbe78a8d82f29979031a4aa0b16a9d"
  }

  @Test
  fun part2() {
    Day10.solvePart2() shouldBe "62e2204d2ca4f4924f6e7a80f1288786"
  }
}
