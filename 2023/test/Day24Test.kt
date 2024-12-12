package aoc.year2023

import aoc.library.solvePart1
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day24Test {

  @Test
  fun part1TestInput() {
    Day24.solvePart1(
      input = """
            19, 13, 30 @ -2,  1, -2
            18, 19, 22 @ -1, -1, -2
            20, 25, 34 @ -2, -2, -4
            12, 31, 28 @ -1, -2, -1
            20, 19, 15 @  1, -5, -3
      """.trimIndent(),
      range = 7L..27L,
    ) shouldBeExactly 2
  }

  @Test
  fun part1() {
    Day24.solvePart1() shouldBeExactly 19523
  }
}
