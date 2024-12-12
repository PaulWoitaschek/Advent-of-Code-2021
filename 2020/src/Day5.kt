package aoc.year2020

import aoc.library.Puzzle
import kotlin.math.ceil

object Day5 : Puzzle<Int, Int>(5) {

  override fun solvePart1(input: String): Int = boardingPasses(input).max()

  override fun solvePart2(input: String): Int = boardingPasses(input)
    .sorted()
    .zipWithNext()
    .single { (current, next) ->
      next != current + 1
    }
    .first + 1

  private fun boardingPasses(input: String): List<Int> = input.lines().filter { it.isNotEmpty() }
    .map { boardingPass ->
      val row = findValue(127, boardingPass.take(7))
      val column = findValue(7, boardingPass.drop(7))
      seatId(row, column)
    }

  private fun seatId(
    row: Int,
    column: Int,
  ): Int = row * 8 + column

  private fun findValue(
    max: Int,
    instructions: String,
  ): Int {
    var from = 0
    var to = max
    instructions.forEach {
      val range = ceil((to.toDouble() - from) / 2).toInt()
      if (it == 'F' || it == 'L') {
        to -= range
      } else {
        from += range
      }
    }
    return from
  }
}
