package aoc.year2020

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day8Test {

  @Test
  fun part1() {
    Day8.solvePart1() shouldBe 1928
    Day8.solvePart1(
      """
nop +0
acc +1
jmp +4
acc +3
jmp -3
acc -99
acc +1
jmp -4
acc +6
      """.trimIndent(),
    ) shouldBe 5
  }

  @Test
  fun part2() {
    Day8.solvePart2() shouldBe 1319
    Day8.solvePart2(
      """
nop +0
acc +1
jmp +4
acc +3
jmp -3
acc -99
acc +1
jmp -4
acc +6
      """.trimIndent(),
    ) shouldBe 8
  }
}
