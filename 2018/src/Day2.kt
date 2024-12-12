package aoc.year2018

import aoc.library.Puzzle

object Day2 : Puzzle<Int, String>(2) {

  override fun solvePart1(input: String): Int {
    var pairs = 0
    var triples = 0
    input.lines()
      .forEach { line ->
        val counts = line.groupingBy { it }.eachCount().values
        if (2 in counts) pairs++
        if (3 in counts) triples++
      }
    return pairs * triples
  }

  override fun solvePart2(input: String): String {
    val pool = input.lines()
      .map { it.toList() }
      .toMutableList()
    while (true) {
      val toCheck = pool.removeFirst()
      val result = pool.firstNotNullOfOrNull { match(toCheck, it) }
      if (result != null) {
        return result
      }
    }
  }

  private fun match(
    left: List<Char>,
    right: List<Char>,
  ): String? {
    var misMatchIndex = -1
    left.forEachIndexed { index, leftValue ->
      if (leftValue != right[index]) {
        if (misMatchIndex != -1) {
          return null
        }
        misMatchIndex = index
      }
    }
    return left.toMutableList()
      .apply { removeAt(misMatchIndex) }
      .joinToString("")
  }
}
