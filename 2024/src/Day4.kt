package aoc.year2024

import aoc.library.Point
import aoc.library.Puzzle

object Day4 : Puzzle<Long, Long>(day = 4) {

  override fun solvePart1(input: String): Long {
    val lines = input.lines()
    var count = 0L
    val allDirections = Point(0, 0).adjacent()

    lines.forEachIndexed { y, line ->
      line.forEachIndexed { x, char ->
        if (char == 'X') {
          val from = Point(x, y)
          count += allDirections.count { direction ->
            val result = generateSequence(from) { it + direction }
              .take(4)
              .mapNotNull { lines.getOrNull(it.y)?.getOrNull(it.x) }
              .joinToString("")
            result == "XMAS"
          }
        }
      }
    }

    return count
  }

  override fun solvePart2(input: String): Long {
    val lines = input.lines()
    var count = 0L

    lines.forEachIndexed { y, line ->
      line.forEachIndexed { x, char ->
        if (char == 'A') {
          val topLeftAndBottomRight = listOf(Point(x - 1, y - 1), Point(x + 1, y + 1))
          val bottomLeftAndTopRight = listOf(Point(x - 1, y + 1), Point(x + 1, y - 1))
          val diagonalsOnlySM = listOf(topLeftAndBottomRight, bottomLeftAndTopRight)
            .map { points ->
              points.map { lines.getOrNull(it.y)?.getOrNull(it.x) }.toSet()
            }
            .all { it == setOf('S', 'M') }

          if (diagonalsOnlySM) count++
        }
      }
    }

    return count
  }
}
