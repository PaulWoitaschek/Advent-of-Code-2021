package aoc.year2023

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day15Test {

  private val testInput = "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7"

  @Test
  fun part1TestInput() {
    Day15.solvePart1(testInput) shouldBeExactly 1320
  }

  @Test
  fun hash() {
    Day15.hash("HASH") shouldBeExactly 52
  }

  @Test
  fun part1() {
    Day15.solvePart1() shouldBeExactly 515974
  }

  @Test
  fun part2TestInput() {
    Day15.solvePart2(testInput) shouldBeExactly 145
  }

  @Test
  fun part2() {
    Day15.solvePart2() shouldBeExactly 265894
  }
}
