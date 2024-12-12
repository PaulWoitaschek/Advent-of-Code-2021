package aoc.year2020

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day15Test {

  @Test
  fun part1() {
    Day15.solvePart1("0,3,6") shouldBe 436
    Day15.solvePart1("11,18,0,20,1,7,16") shouldBe 639
  }

  @Test
  @Disabled("Too slow")
  fun part2() {
    Day15.solvePart2("11,18,0,20,1,7,16") shouldBe 266
  }
}
