package aoc.year2024

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.longs.shouldBeExactly
import org.junit.jupiter.api.Test

class Day9Test {

  @Test
  fun part1TestInput() {
    Day9.solvePart1("2333133121414131402") shouldBeExactly 1928
  }

  @Test
  fun part1() {
    Day9.solvePart1() shouldBeExactly 6154342787400
  }

  @Test
  fun part2TestInput() {
    Day9.solvePart2("2333133121414131402") shouldBeExactly 2858
  }

  @Test
  fun part2() {
    Day9.solvePart2() shouldBeExactly 6183632723350
  }
}
