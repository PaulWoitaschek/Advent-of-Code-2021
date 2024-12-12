package aoc.year2015

import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day4Test {

  @Test
  fun part1() {
    Day4.solvePart1("bgvyzdsv") shouldBeExactly 254575
  }

  @Test
  fun part1Test1() {
    Day4.solvePart1("abcdef") shouldBeExactly 609043
  }

  @Test
  fun part1Test2() {
    Day4.solvePart1("pqrstuv") shouldBeExactly 1048970
  }

  @Test
  fun part2() {
    Day4.solvePart2("bgvyzdsv") shouldBeExactly 1038736
  }
}
