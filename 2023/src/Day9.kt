package aoc.year2023

import aoc.library.Puzzle

object Day9 : Puzzle<Long, Long>(day = 9) {

  override fun solvePart1(input: String): Long = solve(input = input, addRight = true)
  override fun solvePart2(input: String): Long = solve(input = input, addRight = false)

  private fun solve(
    input: String,
    addRight: Boolean,
  ): Long = input.lines().sumOf { line ->
    val history = line.split(" ").map(String::toLong)
    predict(if (addRight) history else history.reversed())
  }

  private tailrec fun predict(
    history: List<Long>,
    acc: Long = 0,
  ): Long = if (history.all { it == 0L }) {
    acc
  } else {
    predict(history.zipWithNext { a, b -> b - a }, history.last() + acc)
  }
}
