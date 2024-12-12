package aoc.year2021

import aoc.library.Puzzle

object Day1 : Puzzle<Int, Int>(1) {

  override fun solvePart1(input: String): Int = input.lines().map(String::toInt)
    .zipWithNext { previous, current -> current > previous }
    .count { it }

  override fun solvePart2(input: String): Int = input.lines().map(String::toInt)
    .windowed(3) { it.sum() }
    .zipWithNext { previous, current -> current > previous }
    .count { it }
}
