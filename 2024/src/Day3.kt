package aoc.year2024

import aoc.library.Puzzle

object Day3 : Puzzle<Long, Long>(day = 3) {

  override fun solvePart1(input: String): Long = runMultiplications(input)

  override fun solvePart2(input: String): Long = runMultiplications(clean(input))

  private fun clean(input: String): String {
    val firstDont = input.indexOf("don't()")
    if (firstDont == -1) return input
    val firstDo = input.indexOf("do()", startIndex = firstDont)
    if (firstDo == -1) return input
    return clean(input.take(firstDont) + input.drop(firstDo + 4))
  }

  private fun runMultiplications(input: String): Long {
    val regex = """mul\((\d+),(\d+)\)""".toRegex()
    return regex.findAll(input).map {
      val (x, y) = it.destructured
      x.toLong() * y.toLong()
    }.sum()
  }
}
