package aoc.year2019

import aoc.library.Direction
import aoc.library.Point
import aoc.library.Puzzle
import aoc.library.move

object Day3 : Puzzle<Int, Int>(3) {

  override fun solvePart1(input: String): Int {
    val paths = paths(input)
    return intersections(paths)
      .filter { it != Point.Zero }
      .minOf {
        it.manhattanDistanceTo(Point(0, 0))
      }
  }

  override fun solvePart2(input: String): Int {
    val paths = paths(input)
    return intersections(paths)
      .filter { it != Point.Zero }
      .minOf { intersection ->
        paths.sumOf { line ->
          line.indexOf(intersection)
            .coerceAtLeast(0)
        }
      }
  }

  private fun paths(input: String): List<List<Point>> = input.lines()
    .map { vectorString ->
      val path = mutableListOf(Point.Zero)
      vectorString.split(",").forEach { vector ->
        val direction = Direction.parse(vector.first())
        repeat(vector.drop(1).toInt()) {
          path += path.last().move(direction)
        }
      }
      path
    }

  private fun intersections(paths: List<List<Point>>): Set<Point> = paths.indices.flatMap { leftIndex ->
    paths.indices.flatMap { rightIndex ->
      if (leftIndex != rightIndex) {
        paths[leftIndex].intersect(paths[rightIndex].toSet())
      } else {
        emptySet()
      }
    }
  }.toSet()
}
