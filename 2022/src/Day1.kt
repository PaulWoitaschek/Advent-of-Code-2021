package aoc.year2022

import aoc.library.Puzzle

object Day1 : Puzzle<Int, Int>(1) {
  override fun solvePart1(input: String) = parse(input).max()
  override fun solvePart2(input: String) = parse(input).sortedDescending().take(3).sum()
}

private fun parse(input: String): List<Int> = input.split("\n\n")
  .map { elv ->
    elv.split("\n").sumOf { line ->
      line.toIntOrNull() ?: 0
    }
  }
