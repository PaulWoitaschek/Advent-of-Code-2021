package de.woitaschek.aoc.year2019

import de.woitaschek.aoc.test
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day16Test {

  @Test
  fun onePhase() {
    Day16.process(input = "12345678", phases = 1) shouldBe "48226158"
  }

  @Test
  fun twoPhases() {
    Day16.process(input = "12345678", phases = 2) shouldBe "34040438"
  }

  @Test
  fun part1Test() {
    Day16.solvePart1("80871224585914546619083218645595") shouldBe "24176176"
  }

  @Test
  fun part1() {
    Day16.test(part1 = "15841929")
  }
}
