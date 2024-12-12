package aoc.year2018

import aoc.library.Puzzle

object Day1 : Puzzle<Int, Int>(day = 1) {

  override fun solvePart1(input: String): Int = input.lines().map(String::toInt).fold(0, Int::plus)

  override fun solvePart2(input: String): Int {
    val values = input.lines().map(String::toInt)
    val emitted = mutableSetOf<Int>()
    return generateSequence { values }.flatten()
      .runningFold(0, Int::plus)
      .first {
        !emitted.add(it)
      }
  }
}
