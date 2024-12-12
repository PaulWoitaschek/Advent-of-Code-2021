package aoc.year2020

import aoc.library.Puzzle

class Day9(private val premableLength: Int) : Puzzle<Int, Int>(9) {

  override fun solvePart1(input: String) = invalidNumber(input)

  override fun solvePart2(input: String): Int {
    val invalidNumber = invalidNumber(input)
    input.lineSequence().mapNotNull(String::toIntOrNull)
      .windowed(invalidNumber, 1, true)
      .forEach a@{ window ->
        val result = mutableListOf<Int>()
        window.forEach {
          result.add(it)
          val sum = result.sum()
          if (sum == invalidNumber) {
            return result.min() + result.max()
          } else if (sum > invalidNumber) {
            return@a
          }
        }
      }
    error("No secret number")
  }

  private fun invalidNumber(input: String) = input.lineSequence()
    .mapNotNull(String::toIntOrNull)
    .windowed(premableLength + 1)
    .first { !it.dropLast(1).hasSumPair(it.last()) }
    .last()
}

private fun List<Int>.hasSumPair(sum: Int): Boolean {
  forEachIndexed { xIndex, x ->
    forEachIndexed { yIndex, y ->
      if (xIndex != yIndex && x + y == sum) {
        return true
      }
    }
  }
  return false
}
