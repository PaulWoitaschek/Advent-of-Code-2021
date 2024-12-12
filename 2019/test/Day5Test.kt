package aoc.year2019

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day5Test {

  @Test
  fun part1() {
    Day5.solvePart1() shouldBe 12440243L
  }

  @Test
  fun part2() {
    Day5.solvePart2() shouldBe 15486302L
  }

  @Test
  fun positionModeZero() {
    runTest(
      instructions = listOf(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9),
      input = 0,
      expectedOutput = 0,
    )
  }

  @Test
  fun positionModeOne() {
    runTest(instructions = listOf(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9), input = 42, expectedOutput = 1)
  }

  @Test
  fun immediateModeZero() {
    runTest(instructions = listOf(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1), input = 0, expectedOutput = 0)
  }

  @Test
  fun immediateModeOne() {
    runTest(
      instructions = listOf(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1),
      input = 42,
      expectedOutput = 1,
    )
  }

  @Test
  fun largerExampleBelow8() {
    runTest(
      instructions = listOf(
        3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
        1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
        999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99,
      ),
      input = 7,
      expectedOutput = 999,
    )
  }

  @Test
  fun largerExampleExactly8() {
    runTest(
      instructions = listOf(
        3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
        1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
        999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99,
      ),
      input = 8,
      expectedOutput = 1000,
    )
  }

  @Test
  fun largerExampleHigherThan8() {
    runTest(
      instructions = listOf(
        3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
        1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
        999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99,
      ),
      input = 9,
      expectedOutput = 1001,
    )
  }

  @Test
  fun equalEightPositionMode() {
    runTest(
      instructions = listOf(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8),
      input = 8,
      expectedOutput = 1,
    )
  }

  @Test
  fun notEqualEightPositionMode() {
    runTest(
      instructions = listOf(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8),
      input = 9,
      expectedOutput = 0,
    )
  }

  @Test
  fun positionModeLessThan() {
    runTest(
      instructions = listOf(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8),
      input = 7,
      expectedOutput = 1,
    )
  }

  private fun runTest(
    instructions: List<Long>,
    input: Long,
    expectedOutput: Int,
  ) {
    Day5.solve(instructions, input) shouldBe expectedOutput
  }
}
