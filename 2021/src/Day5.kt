package aoc.year2021

import aoc.library.Point
import aoc.library.Puzzle
import kotlin.math.abs

object Day5 : Puzzle<Long, Long>(5) {

  override fun solvePart1(input: String): Long = process(input, includeDiagonals = false)

  override fun solvePart2(input: String): Long = process(input, includeDiagonals = true)
}

private fun process(
  input: String,
  includeDiagonals: Boolean,
): Long {
  val lines = input.lines().map(Line.Companion::parse)

  val pointScores = mutableMapOf<Point, Int>()
  lines.forEach { line ->
    line.pointsOnLine(includeDiagonals = includeDiagonals)
      .forEach { point ->
        pointScores[point] = pointScores.getOrPut(point) { 0 } + 1
      }
  }
  return pointScores.count { it.value >= 2 }.toLong()
}

private data class Line(val from: Point, val to: Point) {

  fun pointsOnLine(includeDiagonals: Boolean): List<Point> = when {
    from.x == to.x -> {
      (minOf(from.y, to.y)..maxOf(from.y, to.y)).map { rangeY ->
        Point(x = from.x, y = rangeY)
      }
    }

    from.y == to.y -> {
      (minOf(from.x, to.x)..maxOf(from.x, to.x)).map { rangeX ->
        Point(x = rangeX, y = from.y)
      }
    }

    includeDiagonals && (abs(from.x - to.x) == abs(from.y - to.y)) -> {
      val results = mutableListOf<Point>()
      results += from
      var currentPoint = from
      val up = from.y < to.y
      val right = from.x < to.x
      do {
        currentPoint = Point(
          x = currentPoint.x + if (right) 1 else -1,
          y = currentPoint.y + if (up) 1 else -1,
        )
        results += currentPoint
      } while (currentPoint != to)
      results
    }

    else -> {
      emptyList()
    }
  }

  companion object {
    fun parse(input: String): Line {
      val (from, to) = input.split(" -> ")
      return Line(
        from = Point.parse(from),
        to = Point.parse(to),
      )
    }
  }
}
