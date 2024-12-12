package aoc.year2023

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day16Test {

  private val testInput = """
      .|...\....
      |.-.\.....
      .....|-...
      ........|.
      ..........
      .........\
      ..../.\\..
      .-.-/..|..
      .|....-|.\
      ..//.|....
  """.trimIndent()

  @Test
  fun part1TestInput() {
    Day16.solvePart1(testInput) shouldBeExactly 46
  }

  @Test
  fun part1() {
    Day16.solvePart1() shouldBeExactly 6740
  }

  @Test
  fun part2TestInput() {
    Day16.solvePart2(testInput) shouldBeExactly 51
  }

  @Test
  fun part2() {
    Day16.solvePart2() shouldBeExactly 7041
  }
}
