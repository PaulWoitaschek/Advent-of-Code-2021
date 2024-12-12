package aoc.year2020

import aoc.library.Puzzle

object Day10 : Puzzle<Int, Long>(10) {

  override fun solvePart1(input: String): Int {
    val differences = powerConnections(input).zipWithNext { first, second ->
      second - first
    }
    return differences.count { it == 1 } * differences.count { it == 3 }
  }

  override fun solvePart2(input: String): Long {
    val paths = mutableMapOf(0 to 1L)
    val connections = powerConnections(input)
    connections.drop(1).forEach { connection ->
      paths[connection] = (1..3).sumOf { step ->
        paths.getOrDefault(connection - step, 0)
      }
    }
    return paths.getValue(connections.last())
  }

  private fun powerConnections(input: String): List<Int> = input.lines().filter { it.isNotEmpty() }
    .map { it.toInt() }
    .sorted()
    .let {
      it.toMutableList().apply {
        add(0, 0)
        add(last() + 3)
      }
    }
}
