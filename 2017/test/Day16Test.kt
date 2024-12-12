package aoc.year2017

import aoc.library.solvePart1
import aoc.library.solvePart2
import aoc.year2017.Day16.solve
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day16Test {

  @Test
  fun part1TestInput() {
    solve("s1", programCount = 5, danceRounds = 1) shouldBe "eabcd"
    solve("s1,x3/4", programCount = 5, danceRounds = 1) shouldBe "eabdc"
    solve("s1,x4/3", programCount = 5, danceRounds = 1) shouldBe "eabdc"
    solve("s1,x3/4,pe/b", programCount = 5, danceRounds = 1) shouldBe "baedc"
    solve("s1,x3/4,pb/e", programCount = 5, danceRounds = 1) shouldBe "baedc"
  }

  @Test
  fun part1() {
    Day16.solvePart1() shouldBe "pkgnhomelfdibjac"
  }

  @Test
  fun part2() {
    Day16.solvePart2() shouldBe "pogbjfihclkemadn"
  }
}
