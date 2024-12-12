package aoc.year2022

import aoc.library.Puzzle

object Day3 : Puzzle<Int, Int>(3) {

  override fun solvePart1(input: String) = input.lines()
    .filter(String::isNotEmpty).sumOf { line ->
      val left = line.substring(0, line.length / 2)
      val right = line.substring(line.length / 2, line.length)
      left.toSet().intersect(right.toSet()).single().score()
    }

  override fun solvePart2(input: String) = input.lines()
    .filter(String::isNotEmpty)
    .chunked(3)
    .sumOf {
      it.reduce { previous, current ->
        previous.toSet().intersect(current.toSet()).joinToString()
      }.single().score()
    }
}

private fun Char.score(): Int = if (isLowerCase()) {
  this - 'a' + 1
} else {
  this - 'A' + 27
}
