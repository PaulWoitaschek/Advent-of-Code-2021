package aoc.year2024

import aoc.library.Point
import aoc.library.Puzzle

object Day10 : Puzzle<Int, Int>(day = 10) {

  override fun solvePart1(input: String): Int = findTrailPaths(input, countUniqueTrails = true)

  override fun solvePart2(input: String): Int = findTrailPaths(input, countUniqueTrails = false)

  private fun findTrailPaths(
    input: String,
    countUniqueTrails: Boolean,
  ): Int {
    val mountains = input.lines().flatMapIndexed { y, line ->
      line.mapIndexed { x, char ->
        Point(x, y) to char.digitToIntOrNull()
      }
    }.toMap()

    fun visit(
      currentHeight: Int,
      point: Point,
      reachedNines: MutableList<Point>,
    ) {
      val heightAtPoint = mountains[point] ?: return
      if (heightAtPoint == currentHeight + 1) {
        if (heightAtPoint == 9) {
          reachedNines += point
        } else {
          point.adjacentOrthogonal().forEach { adjacent ->
            visit(heightAtPoint, adjacent, reachedNines)
          }
        }
      }
    }
    return mountains.toList()
      .filter { (_, height) -> height == 0 }
      .sumOf { (start, _) ->
        val reachedNines = mutableListOf<Point>()
        visit(-1, start, reachedNines)
        if (countUniqueTrails) reachedNines.toSet().size else reachedNines.size
      }
  }
}
