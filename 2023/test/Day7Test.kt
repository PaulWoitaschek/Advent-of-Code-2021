package aoc.year2023

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day7Test {

  private val testInput: String = """
    32T3K 765
    T55J5 684
    KK677 28
    KTJJT 220
    QQQJA 483
  """.trimIndent()

  @Test
  fun part1() {
    Day7.solvePart1() shouldBeExactly 252656917
  }

  @Test
  fun part1TestInput() {
    Day7.solvePart1(testInput) shouldBeExactly 6440
  }

  @Test
  fun part2() {
    Day7.solvePart2() shouldBeExactly 253499763
  }

  @Test
  fun part2TestInput() {
    Day7.solvePart2(testInput) shouldBeExactly 5905
  }
}
