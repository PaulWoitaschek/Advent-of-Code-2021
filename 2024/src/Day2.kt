package aoc.year2024

import aoc.library.Puzzle
import kotlin.math.absoluteValue

object Day2 : Puzzle<Int, Int>(day = 2) {

  override fun solvePart1(input: String): Int = parse(input).count { reportIsSafe(it) }

  override fun solvePart2(input: String): Int = parse(input)
    .count { report ->
      report.indices.any { removeIndex ->
        val adjustedReport = report.toMutableList()
          .apply { removeAt(removeIndex) }
        reportIsSafe(adjustedReport)
      }
    }

  private fun reportIsSafe(report: List<Int>): Boolean {
    val steps = report.zipWithNext { left, right -> left - right }
    val monotonic = steps.all { it > 0 } || steps.all { it < 0 }
    return monotonic && steps.all { it.absoluteValue in 1..3 }
  }

  private fun parse(input: String): List<List<Int>> = input.lines().map {
    it.split(" ").map(String::toInt)
  }
}
