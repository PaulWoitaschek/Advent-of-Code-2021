package aoc.year2020

import aoc.library.solvePart1
import io.kotest.matchers.longs.shouldBeExactly
import org.junit.jupiter.api.Test

class Day14Test {

  @Test
  fun part1() {
    Day14.solvePart1() shouldBeExactly 10452688630537
  }

  @Test
  fun part1TestInput() {
    Day14.solvePart1(
      """
      mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
      mem[8] = 11
      mem[7] = 101
      mem[8] = 0
      """.trimIndent(),
    ) shouldBeExactly 165
  }
}
