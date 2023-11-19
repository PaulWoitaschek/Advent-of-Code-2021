package de.woitaschek.aoc.year2019

import de.woitaschek.aoc.utils.Point
import de.woitaschek.aoc.utils.Puzzle
import de.woitaschek.aoc.utils.toLineList
import kotlin.math.atan2

object Day10 : Puzzle<Int, Int>(2019, 10) {

  override fun solvePart1(input: String): Int {
    val asteroids = parseAsteroids(input)
    return asteroids.maxOf { target ->
      asteroids.groupBy { target.degreesTo(it) }.size
    }
  }

  override fun solvePart2(input: String): Int {
    val asteroids = parseAsteroids(input)
    val station = asteroids.maxBy { target ->
      asteroids.groupBy { target.degreesTo(it) }.size
    }
    val otherAsteroids = asteroids
      .groupBy { station.degreesTo(it) }
      .mapValues { (_, asteroids) ->
        asteroids
          .sortedBy { station.manhattanDistanceTo(it) }
          .toMutableList()
      }
      .toList()
      .sortedBy { (degrees, _) -> degrees }

    var killCount = 0
    while (true) {
      otherAsteroids.forEach {
        val killedAsteroid = it.second.removeFirstOrNull()
        if (killedAsteroid != null) {
          if (++killCount == 200) {
            return killedAsteroid.x * 100 + killedAsteroid.y
          }
        }
      }
    }
  }

  private fun parseAsteroids(input: String): List<Point> {
    return input.toLineList()
      .flatMapIndexed { y, line ->
        line.mapIndexedNotNull { x, value ->
          if (value == '#') {
            Point(x, y)
          } else {
            null
          }
        }
      }
  }

  private fun Point.degreesTo(other: Point): Double {
    val atan2 = atan2(
      y = (other.y - this.y).toDouble(),
      x = (other.x - this.x).toDouble(),
    )
    return (Math.toDegrees(atan2) + 90 + 360) % 360
  }
}
