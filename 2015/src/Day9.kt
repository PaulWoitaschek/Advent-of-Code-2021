package aoc.year2015

import aoc.library.Puzzle

object Day9 : Puzzle<Int, Int>(9) {

  override fun solvePart1(input: String): Int {
    var min = Int.MAX_VALUE
    onEachPath(input) {
      min = minOf(it, min)
    }
    return min
  }

  override fun solvePart2(input: String): Int {
    var max = Int.MIN_VALUE
    onEachPath(input) {
      max = maxOf(it, max)
    }
    return max
  }

  private fun onEachPath(
    input: String,
    onPathCompleted: (Int) -> Unit,
  ) {
    val routes = input.lines()
      .map(Route::parse)
      .flatMap {
        listOf(it, it.copy(from = it.to, to = it.from))
      }
    val cities = routes.flatMap { listOf(it.from, it.to) }.toSet()
    fun walk(
      from: String,
      visited: Set<String>,
      distance: Int,
    ) {
      if (visited.size == cities.size) {
        onPathCompleted(distance)
        return
      }
      routes.forEach { route ->
        val to = route.to
        if (route.from == from && route.to !in visited) {
          walk(
            from = to,
            visited = visited + to,
            distance = distance + route.distance,
          )
        }
      }
    }
    cities.forEach {
      walk(it, setOf(it), 0)
    }
  }

  data class Route(val from: String, val to: String, val distance: Int) {
    companion object {
      private val pattern = "(.*?) to (.*?) = (\\d+)".toRegex()
      fun parse(input: String): Route {
        val (from, to, distance) = pattern.find(input)!!.destructured
        return Route(from = from, to = to, distance = distance.toInt())
      }
    }
  }
}
