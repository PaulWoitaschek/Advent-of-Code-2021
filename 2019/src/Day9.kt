package aoc.year2019

import aoc.library.Puzzle
import kotlinx.coroutines.runBlocking

object Day9 : Puzzle<Long, Long>(9) {

  override fun solvePart1(input: String): Long {
    val values = input.split(",").map(String::toLong)
    val computer = IntCodeComputer(instructions = values, inputs = listOf(1))
    runBlocking {
      computer.run()
    }
    return computer.concatFullOutput
  }

  override fun solvePart2(input: String): Long {
    val values = input.split(",").map(String::toLong)
    val computer = IntCodeComputer(instructions = values, inputs = listOf(2))
    runBlocking {
      computer.run()
    }
    return computer.concatFullOutput
  }
}
