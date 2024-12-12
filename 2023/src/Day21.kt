package aoc.year2023

import aoc.library.Direction
import aoc.library.Point
import aoc.library.Puzzle
import aoc.library.move

object Day21 : Puzzle<Int, Long>(day = 21) {

  override fun solvePart1(input: String): Int = positionsAfterSteps(input, 64)

  override fun solvePart2(input: String): Long = TODO()

  fun positionsAfterSteps(
    input: String,
    steps: Int,
  ): Int {
    val gardenMap = GardenMap(input)
    var positions = setOf(gardenMap.startingPoint())
    repeat(steps) {
      val new = mutableSetOf<Point>()
      positions.forEach { position ->
        Direction.entries.forEach { direction ->
          val newPosition = position.move(direction)
          if (gardenMap.isGarden(newPosition.x, newPosition.y)) {
            new.add(newPosition)
          }
        }
      }
      positions = new
    }

    return positions.toSet().size
  }

  private class GardenMap(input: String) {

    private val lines = input.lines()
    private val width = lines.first().length
    private val height = lines.size

    fun startingPoint(): Point {
      lines.forEachIndexed { y, line ->
        line.forEachIndexed { x, char ->
          if (char == 'S') return Point(x, y)
        }
      }
      error("Missing starting point")
    }

    fun isGarden(
      x: Int,
      y: Int,
    ): Boolean {
      val value = lines[y.mod(height)][x.mod(width)]
      return value == '.' || value == 'S'
    }
  }
}
