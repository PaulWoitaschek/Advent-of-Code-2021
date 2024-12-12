package aoc.year2020

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day9Test {

  @Test
  fun part1TestInput() {
    Day9(5).solvePart1(
      """
35
20
15
25
47
40
62
55
65
95
102
117
150
182
127
219
299
277
309
576
      """.trimIndent(),
    ) shouldBe 127
  }

  @Test
  fun part2TestInput() {
    Day9(5).solvePart2(
      """
35
20
15
25
47
40
62
55
65
95
102
117
150
182
127
219
299
277
309
576
      """.trimIndent(),
    ) shouldBe 62
  }

  @Test
  fun part1() {
    Day9(25).solvePart1() shouldBe 257342611
  }

  @Test
  fun part2() {
    Day9(25).solvePart2() shouldBe 35602097
  }
}
