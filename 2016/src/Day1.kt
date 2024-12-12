package aoc.year2016

import aoc.library.Direction
import aoc.library.Point
import aoc.library.Puzzle
import aoc.library.move

object Day1 : Puzzle<Int, Int>(day = 1) {

  override fun solvePart1(input: String): Int = walk(input).manhattanDistanceTo(Point.Zero)

  override fun solvePart2(input: String): Int {
    val visited = mutableSetOf<Point>()
    return walk(input) {
      !visited.add(it)
    }.manhattanDistanceTo(Point.Zero)
  }

  private inline fun walk(
    input: String,
    shouldStop: (Point) -> Boolean = { false },
  ): Point {
    var position = Point.Zero
    var facing = Direction.Up
    input.split(", ").forEach { line ->
      val turning = line.first()
      val steps = line.drop(1).toInt()
      facing = when (turning) {
        'R' -> facing.clockwise()
        'L' -> facing.counterClockwise()
        else -> error("Invalid $turning")
      }
      repeat(steps) {
        position = position.move(facing)
        if (shouldStop(position)) {
          return position
        }
      }
    }
    return position
  }
}
