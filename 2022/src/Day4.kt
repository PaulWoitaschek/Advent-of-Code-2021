package aoc.year2022

import aoc.library.Puzzle

object Day4 : Puzzle<Int, Int>(4) {

  override fun solvePart1(input: String) = input.rangeMatches {
    (x1 >= y1 && x2 <= y2) || (y1 >= x1 && y2 <= x2)
  }

  override fun solvePart2(input: String) = input.rangeMatches {
    x2 >= y1 && x1 <= y2
  }
}

private data class Ranges(val x1: Int, val x2: Int, val y1: Int, val y2: Int)

private inline fun String.rangeMatches(match: Ranges.() -> Boolean): Int = lines().filter(String::isNotEmpty).count { line ->
  val values = line.split(",", "-").map { it.toInt() }
  match(Ranges(values[0], values[1], values[2], values[3]))
}
