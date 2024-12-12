package aoc.year2023

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.longs.shouldBeExactly
import org.junit.jupiter.api.Test

class Day8Test {

  @Test
  fun part1() {
    Day8.solvePart1() shouldBeExactly 20221
  }

  @Test
  fun part1TestInput() {
    Day8.solvePart1(
      """
      LLR

      AAA = (BBB, BBB)
      BBB = (AAA, ZZZ)
      ZZZ = (ZZZ, ZZZ)
      """.trimIndent(),
    ) shouldBeExactly 6
  }

  @Test
  fun part2() {
    Day8.solvePart2() shouldBeExactly 14616363770447
  }

  @Test
  fun part2TestInput() {
    Day8.solvePart2(
      """
      LR

      11A = (11B, XXX)
      11B = (XXX, 11Z)
      11Z = (11B, XXX)
      22A = (22B, XXX)
      22B = (22C, 22C)
      22C = (22Z, 22Z)
      22Z = (22B, 22B)
      XXX = (XXX, XXX)
      """.trimIndent(),
    ) shouldBeExactly 6
  }
}
