package aoc.year2017

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day7Test {

  private val testInput = """
      pbga (66)
      xhth (57)
      ebii (61)
      havc (66)
      ktlj (57)
      fwft (72) -> ktlj, cntj, xhth
      qoyq (66)
      padx (45) -> pbga, havc, qoyq
      tknk (41) -> ugml, padx, fwft
      jptl (61)
      ugml (68) -> gyxo, ebii, jptl
      gyxo (61)
      cntj (57)
  """.trimIndent()

  @Test
  fun part1TestInput() {
    Day7.solvePart1(testInput) shouldBe "tknk"
  }

  @Test
  fun part1() {
    Day7.solvePart1() shouldBe "ahnofa"
  }

  @Test
  @Disabled
  fun part2TestInput() {
    Day7.solvePart2(testInput) shouldBeExactly 8
  }

  @Test
  @Disabled
  fun part2() {
    Day7.solvePart2() shouldBeExactly 42
  }
}
