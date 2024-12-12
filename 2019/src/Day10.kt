package aoc.year2019

import aoc.library.Point
import aoc.library.Puzzle

object Day10 : Puzzle<Int, Int>(10) {

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

  private fun parseAsteroids(input: String): List<Point> = input.lines()
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
