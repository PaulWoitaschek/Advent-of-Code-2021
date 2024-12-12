package aoc.year2020

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day11Test {

  @Test
  fun part1() {
    Day11.solvePart1() shouldBe 2178
    Day11.solvePart1(
      """
L.LL.LL.LL
LLLLLLL.LL
L.L.L..L..
LLLL.LL.LL
L.LL.LL.LL
L.LLLLL.LL
..L.L.....
LLLLLLLLLL
L.LLLLLL.L
L.LLLLL.LL
      """.trimIndent(),
    ) shouldBe 37
  }

  @Test
  fun part2() {
    Day11.solvePart2() shouldBe 1978
    Day11.solvePart2(
      """
L.LL.LL.LL
LLLLLLL.LL
L.L.L..L..
LLLL.LL.LL
L.LL.LL.LL
L.LLLLL.LL
..L.L.....
LLLLLLLLLL
L.LLLLLL.L
L.LLLLL.LL
      """.trimIndent(),
    ) shouldBe 26
  }
}
