package aoc.year2024

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.longs.shouldBeExactly
import org.junit.jupiter.api.Test

class Day3Test {

  @Test
  fun part1TestInput() {
    Day3.solvePart1("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))") shouldBeExactly 161
  }

  @Test
  fun part1() {
    Day3.solvePart1() shouldBeExactly 175015740
  }

  @Test
  fun part2TestInput() {
    Day3.solvePart2("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))") shouldBeExactly 48
  }

  @Test
  fun part2() {
    Day3.solvePart2() shouldBeExactly 112272912
  }
}
