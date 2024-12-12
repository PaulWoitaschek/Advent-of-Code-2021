package aoc.year2017

import aoc.library.Puzzle

object Day2 : Puzzle<Int, Int>(day = 2) {

  override fun solvePart1(input: String): Int = parse(input).sumOf { it.max() - it.min() }

  override fun solvePart2(input: String): Int = parse(input).sumOf { numbers ->
    numbers.firstNotNullOf { number ->
      numbers.firstOrNull { other ->
        other != number && number.rem(other) == 0
      }?.let { number / it }
    }
  }

  private fun parse(input: String): List<List<Int>> {
    val numberRegex = """(\d+)""".toRegex()
    return input.lines()
      .map { line ->
        numberRegex.findAll(line).map { it.value.toInt() }.toList()
      }
  }
}
