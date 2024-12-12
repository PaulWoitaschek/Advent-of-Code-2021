package aoc.year2018

import aoc.library.Point
import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.longs.shouldBeExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day11Test {

  @Test
  fun powerLevel() {
    Day11.powerLevel(Point(122, 79), 57) shouldBeExactly -5
    Day11.powerLevel(Point(217, 196), 39) shouldBeExactly 0
    Day11.powerLevel(Point(101, 153), 71) shouldBeExactly 4
  }

  @Test
  fun part1TestInput() {
    Day11.solvePart1("18") shouldBe "33,45"
    Day11.solvePart1("42") shouldBe "21,61"
  }

  @Test
  fun part1() {
    Day11.solvePart1() shouldBe "34,13"
  }

  @Test
  @Disabled
  fun part2TestInput() {
    Day11.solvePart2() shouldBeExactly 42
  }

  @Test
  @Disabled
  fun part2() {
    Day11.solvePart2() shouldBeExactly 42
  }
}
