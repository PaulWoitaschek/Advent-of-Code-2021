package aoc.year2024

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.longs.shouldBeExactly
import org.junit.jupiter.api.Test

class Day7Test {

  private val testData = """
      190: 10 19
      3267: 81 40 27
      83: 17 5
      156: 15 6
      7290: 6 8 6 15
      161011: 16 10 13
      192: 17 8 14
      21037: 9 7 18 13
      292: 11 6 16 20
  """.trimIndent()

  @Test
  fun part1TestInput() {
    Day7.solvePart1(testData) shouldBeExactly 3749
  }

  @Test
  fun part1() {
    Day7.solvePart1() shouldBeExactly 2299996598890
  }

  @Test
  fun part2TestInput() {
    Day7.solvePart2(testData) shouldBeExactly 11387
  }

  @Test
  fun part2() {
    Day7.solvePart2() shouldBeExactly 362646859298554
  }
}
