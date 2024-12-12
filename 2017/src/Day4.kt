package aoc.year2017

import aoc.library.Puzzle

object Day4 : Puzzle<Int, Int>(day = 4) {

  override fun solvePart1(input: String): Int = input.lines().count {
    val phrases = it.split(" ")
    containsNoDuplicates(phrases)
  }

  override fun solvePart2(input: String): Int = input.lines().count { line ->
    val sortedPhrases = line.split(" ")
      .map { it.toList().sorted().joinToString("") }
    containsNoDuplicates(sortedPhrases)
  }

  private fun containsNoDuplicates(phrases: List<String>) = phrases.size == phrases.toSet().size
}
