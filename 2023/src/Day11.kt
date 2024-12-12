package aoc.year2023

import aoc.library.Point
import aoc.library.Puzzle
import aoc.library.bounds

object Day11 : Puzzle<Long, Long>(day = 11) {

  override fun solvePart1(input: String): Long = solve(input = input, emptySpace = 2)

  override fun solvePart2(input: String): Long = solve(input = input, emptySpace = 1000000)

  fun solve(
    input: String,
    emptySpace: Int,
  ): Long {
    val galaxies = parse(input)
    val pairs = galaxyPairs(galaxies)
    val galaxyBounds = galaxies.bounds()
    val xGaps = (galaxyBounds.left..galaxyBounds.right).filter { x ->
      galaxies.none { galaxy -> galaxy.x == x }
    }
    val yGaps = (galaxyBounds.top..galaxyBounds.bottom).filter { y ->
      galaxies.none { galaxy -> galaxy.y == y }
    }
    return pairs.sumOf { (left, right) ->
      val xGapAdditions = (minOf(left.x, right.x)..maxOf(left.x, right.x))
        .count { it in xGaps } * (emptySpace - 1)
      val yGapAdditions = (minOf(left.y, right.y)..maxOf(left.y, right.y))
        .count { it in yGaps } * (emptySpace - 1)
      left.manhattanDistanceTo(right).toLong() + xGapAdditions + yGapAdditions
    }
  }

  private fun galaxyPairs(galaxies: Set<Point>): List<Pair<Point, Point>> = galaxies.flatMapIndexed { leftIndex: Int, left: Point ->
    galaxies.drop(leftIndex + 1).map { right ->
      left to right
    }
  }

  private fun parse(input: String): Set<Point> {
    val galaxies = mutableSetOf<Point>()
    input.lines().forEachIndexed { y, line ->
      line.forEachIndexed { x, char ->
        if (char == '#') {
          galaxies.add(Point(x, y))
        }
      }
    }
    return galaxies
  }
}
