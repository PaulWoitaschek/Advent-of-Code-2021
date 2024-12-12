package aoc.year2018

import aoc.library.Point
import aoc.library.Puzzle
import aoc.library.bounds
import aoc.library.printString

object Day10 : Puzzle<String, Int>(day = 10) {

  override fun solvePart1(input: String): String = solve(input).first

  override fun solvePart2(input: String): Int = solve(input).second

  private fun parse(input: String): Pair<List<Point>, List<Point>> {
    val numbersRegex = "(-?\\d+)".toRegex()
    return input.lines()
      .map { line ->
        val (x, y, velX, velY) = numbersRegex.findAll(line).map { it.value.toInt() }.toList()
        Point(x, y) to Point(velX, velY)
      }.unzip()
  }

  private fun solve(input: String): Pair<String, Int> {
    var (points, velocities) = parse(input)

    var seconds = 0
    var bounds = points.bounds()

    while (true) {
      val newPoints = points.mapIndexed { index, point ->
        point + velocities[index]
      }
      val newBounds = newPoints.bounds()
      if (newBounds.size() >= bounds.size()) {
        return points.printString() to seconds
      }

      seconds++
      points = newPoints
      bounds = newBounds
    }
  }
}
