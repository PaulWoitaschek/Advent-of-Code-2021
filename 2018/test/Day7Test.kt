package aoc.year2018

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day7Test {

  private val testInput = """
      Step C must be finished before step A can begin.
      Step C must be finished before step F can begin.
      Step A must be finished before step B can begin.
      Step A must be finished before step D can begin.
      Step B must be finished before step E can begin.
      Step D must be finished before step E can begin.
      Step F must be finished before step E can begin.
  """.trimIndent()

  @Test
  fun part1TestInput() {
    Day7.solvePart1(testInput) shouldBe "CABDFE"
  }

  @Test
  fun part1() {
    Day7.solvePart1() shouldBe "OUGLTKDJVBRMIXSACWYPEQNHZF"
  }

  @Test
  fun part2TestInput() {
    Day7.solvePart2(testInput, workerCount = 2, additionalSeconds = 0) shouldBeExactly 15
  }

  @Test
  fun part2() {
    Day7.solvePart2() shouldBeExactly 929
  }
}
