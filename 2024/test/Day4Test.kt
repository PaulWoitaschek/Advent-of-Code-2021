package aoc.year2024

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.longs.shouldBeExactly
import org.junit.jupiter.api.Test

class Day4Test {

  @Test
  fun part1TestInput() {
    Day4.solvePart1(
      """
            MMMSXXMASM
            MSAMXMSMSA
            AMXSXMAAMM
            MSAMASMSMX
            XMASAMXAMM
            XXAMMXXAMA
            SMSMSASXSS
            SAXAMASAAA
            MAMMMXMMMM
            MXMXAXMASX
      """.trimIndent(),
    ) shouldBeExactly 18
  }

  @Test
  fun part1() {
    Day4.solvePart1() shouldBeExactly 2462
  }

  @Test
  fun part2TestInput() {
    Day4.solvePart2(
      """
            MMMSXXMASM
            MSAMXMSMSA
            AMXSXMAAMM
            MSAMASMSMX
            XMASAMXAMM
            XXAMMXXAMA
            SMSMSASXSS
            SAXAMASAAA
            MAMMMXMMMM
            MXMXAXMASX
      """.trimIndent(),
    ) shouldBeExactly 9
  }

  @Test
  fun part2() {
    Day4.solvePart2() shouldBeExactly 1877
  }
}
