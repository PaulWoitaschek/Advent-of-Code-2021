package aoc.year2023

import aoc.library.Point
import aoc.library.Puzzle

object Day3 : Puzzle<Int, Int>(3) {

  override fun solvePart1(input: String): Int {
    val lines = input.lines()
    return parse(lines)
      .filterNot { number ->
        number.points.all { numberPoint ->
          numberPoint.adjacent().all {
            val char = lines.getOrNull(it.y)?.getOrNull(it.x)
            char == null || char.isDigit() || char == '.'
          }
        }
      }
      .sumOf { it.number }
  }

  override fun solvePart2(input: String): Int {
    val lines = input.lines()
    val numberWithCoordinates = parse(lines)
    var ratios = 0
    lines.forEachIndexed { y, line ->
      line.forEachIndexed { x, char ->
        if (char == '*') {
          val point = Point(x, y)
          val adjacentPoints = point.adjacent()
          val adjacentNumbers = numberWithCoordinates
            .filter { number ->
              number.points.any { numberPoint ->
                numberPoint in adjacentPoints
              }
            }
            .map { it.number }
          if (adjacentNumbers.size == 2) {
            val ratio = adjacentNumbers[0] * adjacentNumbers[1]
            ratios += ratio
          }
        }
      }
    }
    return ratios
  }

  private fun parse(lines: List<String>): List<NumberWithCoordinates> {
    val numberRegex = "\\d+".toRegex()
    return lines.flatMapIndexed { y, line ->
      numberRegex.findAll(line).map { match ->
        NumberWithCoordinates(
          number = match.value.toInt(),
          y = y,
          fromX = match.range.first,
          toX = match.range.last,
        )
      }
    }
  }

  private data class NumberWithCoordinates(val number: Int, val fromX: Int, val toX: Int, val y: Int) {

    val points = (fromX..toX).map { Point(it, y) }
  }
}
