package aoc.year2023

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.longs.shouldBeExactly
import org.junit.jupiter.api.Test

class Day20Test {

  @Test
  fun part1TestInput1() {
    Day20.solvePart1(
      """
      broadcaster -> a, b, c
      %a -> b
      %b -> c
      %c -> inv
      &inv -> a
      """.trimIndent(),
    ) shouldBeExactly 32000000
  }

  @Test
  fun part1TestInput2() {
    Day20.solvePart1(
      """
      broadcaster -> a
      %a -> inv, con
      &inv -> b
      %b -> con
      &con -> output
      """.trimIndent(),
    ) shouldBeExactly 11687500
  }

  @Test
  fun part1() {
    Day20.solvePart1() shouldBeExactly 867118762
  }

  @Test
  fun part2() {
    Day20.solvePart2() shouldBeExactly 217317393039529
  }
}
