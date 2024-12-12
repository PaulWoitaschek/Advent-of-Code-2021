package aoc.year2020

import aoc.library.Puzzle

object Day13 : Puzzle<Int, Int>(13) {

  override fun solvePart1(input: String): Int {
    val lines = input.lines()
    val earliestBus = lines.first().toInt()
    val busses = lines[1].split(",").mapNotNull { it.toIntOrNull() }
    return extracted(earliestBus, busses)
  }

  override fun solvePart2(input: String): Int {
    TODO("Not yet implemented")
  }

  private fun extracted(
    earliestBus: Int,
    busses: List<Int>,
  ): Int = generateSequence(earliestBus) { it + 1 }
    .firstNotNullOf { minute ->
      val bus = busses.find { minute.mod(it) == 0 }
      if (bus != null) {
        (minute - earliestBus) * bus
      } else {
        null
      }
    }
}
