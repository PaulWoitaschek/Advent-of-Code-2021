package aoc.year2017

import aoc.library.Puzzle

object Day12 : Puzzle<Int, Int>(day = 12) {

  override fun solvePart1(input: String): Int = visit(
    connections = parse(input),
    from = 0,
  ).size

  override fun solvePart2(input: String): Int {
    val connections = parse(input)

    val remaining = connections.keys.toMutableSet()

    var groups = 0
    while (remaining.isNotEmpty()) {
      groups++
      val visited = visit(connections, remaining.first())
      remaining.removeAll(visited)
    }

    return groups
  }

  private fun visit(
    connections: Map<Int, Set<Int>>,
    from: Int,
  ): Set<Int> {
    val visited = mutableSetOf<Int>()
    fun visit(from: Int) {
      if (!visited.add(from)) return
      connections[from]!!.forEach {
        visit(it)
      }
    }
    visit(from)
    return visited
  }

  private fun parse(input: String): Map<Int, Set<Int>> = input.lines().associate { line ->
    val (left, right) = line.split(" <-> ")
    val from = left.toInt()
    val targets = right.split(", ").map(String::toInt).toSet()
    from to targets
  }
}
