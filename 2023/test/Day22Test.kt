package aoc.year2023

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day22Test {

  private val testInput = """
      1,0,1~1,2,1
      0,0,2~2,0,2
      0,2,3~2,2,3
      0,0,4~0,2,4
      2,0,5~2,2,5
      0,1,6~2,1,6
      1,1,8~1,1,9
  """.trimIndent()

  @Test
  fun part1TestInput() {
    Day22.solvePart1(testInput) shouldBeExactly 5
  }

  @Test
  fun part1() {
    Day22.solvePart1() shouldBeExactly 386
  }

  @Test
  fun part2TestInput() {
    Day22.solvePart2(testInput) shouldBeExactly 7
  }

  @Test
  @Disabled("Slow^")
  fun part2() {
    Day22.solvePart2() shouldBeExactly 39933
  }
}
