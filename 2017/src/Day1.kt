package aoc.year2017

import aoc.library.Puzzle

object Day1 : Puzzle<Int, Int>(day = 1) {

  override fun solvePart1(input: String): Int = input.map(Char::digitToInt).solveCaptcha(1)

  override fun solvePart2(input: String): Int {
    val numbers = input.map(Char::digitToInt)
    return numbers.solveCaptcha(numbers.size / 2)
  }

  private fun List<Int>.solveCaptcha(step: Int): Int = filterIndexed { index, number ->
    val next = this[(index + step) % size]
    number == next
  }.sum()
}
