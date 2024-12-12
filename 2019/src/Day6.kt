package aoc.year2019

import aoc.library.Puzzle

object Day6 : Puzzle<Int, Int>(6) {

  override fun solvePart1(input: String): Int {
    val orbiter = input.lines()
      .filter { it.isNotEmpty() }
      .associate {
        val (left, right) = it.split(')')
        right to left
      }
    val distanceToComCache = mutableMapOf<String, Int>()
    fun distanceToCom(spaceObject: String): Int = distanceToComCache.getOrPut(spaceObject) {
      if (spaceObject == "COM") {
        0
      } else {
        distanceToCom(orbiter[spaceObject]!!) + 1
      }
    }

    return orbiter.keys.sumOf {
      distanceToCom(it)
    }
  }

  override fun solvePart2(input: String): Int {
    val orbiter = input.lines()
      .filter { it.isNotEmpty() }
      .map {
        val (left, right) = it.split(')')
        right to left
      }

    val neighborCache = mutableMapOf<String, List<String>>()
    fun neighbors(node: String): List<String> = neighborCache.getOrPut(node) {
      orbiter.mapNotNull { (a, b) ->
        when (node) {
          a -> b
          b -> a
          else -> null
        }
      }
    }

    var minDistance = Int.MAX_VALUE
    val target = "SAN"
    fun start(
      from: String,
      path: List<String>,
    ) {
      if (path.size > minDistance) return
      val neighbors = neighbors(from)
      neighbors.forEach { neighbor ->
        if (neighbor == target) {
          minDistance = minOf(path.size, minDistance)
        } else if (neighbor !in path) {
          start(neighbor, path + neighbor)
        }
      }
    }
    start("YOU", listOf("YOU"))
    return minDistance - 2
  }
}
