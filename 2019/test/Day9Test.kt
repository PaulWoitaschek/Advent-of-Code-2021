package aoc.year2019

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.longs.shouldBeExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldHaveLength
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class Day9Test {

  @Test
  fun part1() {
    Day9.solvePart1() shouldBeExactly 3241900951
  }

  @Test
  fun part2() {
    Day9.solvePart2() shouldBeExactly 83089
  }

  @Test
  fun relativeBaseTestOne() {
    val computer = IntCodeComputer(instructions = listOf(104, 1125899906842624, 99), inputs = emptyList())
    runBlocking { computer.run() }
    computer.fullOutput.last() shouldBeExactly 1125899906842624
  }

  @Test
  fun relativeBaseTestTwo() {
    val computer = IntCodeComputer(instructions = listOf(1102, 34915192, 34915192, 7, 4, 7, 99, 0), inputs = emptyList())
    runBlocking { computer.run() }
    computer.fullOutput.last().toString() shouldHaveLength 16
  }

  @Test
  fun relativeBaseTestThree() {
    val instructions: List<Long> = listOf(109, 1, 204, -1, 1001, 100, 1, 100, 1008, 100, 16, 101, 1006, 101, 0, 99)
    val computer =
      IntCodeComputer(instructions = instructions, inputs = emptyList())
    runBlocking { computer.run() }
    computer.fullOutput shouldBe instructions
  }
}
