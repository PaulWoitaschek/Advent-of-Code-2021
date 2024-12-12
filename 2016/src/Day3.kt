package aoc.year2016

import aoc.library.Puzzle
import kotlin.String

object Day3 : Puzzle<Int, Int>(day = 3) {

  override fun solvePart1(input: String): Int = input.lines().count { line ->
    parseNumbers(line).isValidTriangle()
  }

  override fun solvePart2(input: String): Int = input.lines().chunked(3)
    .flatMap { line ->
      val lists = line.map { parseNumbers(it) }
      (0..2).map { index ->
        lists.map { it[index] }
      }
    }.count { it.isValidTriangle() }

  private fun parseNumbers(line: String): List<Int> = """(\d+) +(\d+) +(\d+)""".toRegex()
    .find(line)!!
    .destructured
    .toList()
    .map(String::toInt)

  private fun List<Int>.isValidTriangle(): Boolean {
    val (a, b, c) = this
    return (a + b > c) && (a + c > b) && b + c > a
  }
}
