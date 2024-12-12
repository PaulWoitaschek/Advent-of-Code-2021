package aoc.year2022

import aoc.library.Puzzle
import java.util.PriorityQueue

object Day12 : Puzzle<Int, Int>(12) {

  override fun solvePart1(input: String): Int = solve(input) { it.isStart }

  override fun solvePart2(input: String): Int = solve(input) { it.height == 0 }

  private fun solve(
    input: String,
    isStart: (Vertex) -> Boolean,
  ): Int {
    val vertexes = parseInput(input)
    fun vertex(
      x: Int,
      y: Int,
    ): Vertex? = vertexes.getOrNull(y)?.getOrNull(x)

    fun Vertex.neighborCandidates() = listOfNotNull(
      vertex(x - 1, y),
      vertex(x + 1, y),
      vertex(x, y - 1),
      vertex(x, y + 1),
    ).filter { neighbor ->
      height + 1 >= neighbor.height
    }

    val flatVertexes = vertexes.flatten()
    val end = flatVertexes.first { it.isEnd }
    val start = flatVertexes.filter(isStart).onEach { it.pathLength = 0 }

    val queue = PriorityQueue<Vertex>(compareBy { it.pathLength })
    queue.addAll(start)

    while (true) {
      val vertex = queue.remove()
      if (vertex == end) return end.pathLength
      vertex.neighborCandidates().forEach { neighbor ->
        if (neighbor.pathLength == Int.MAX_VALUE) {
          neighbor.pathLength = vertex.pathLength + 1
          queue.add(neighbor)
        }
      }
    }
  }

  private data class Vertex(val x: Int, val y: Int, var pathLength: Int, val height: Int, val isStart: Boolean, val isEnd: Boolean)

  private fun parseInput(input: String) = input.lines().filter(String::isNotEmpty)
    .mapIndexed { y, line ->
      line.mapIndexed { x, value ->
        val isStart = value == 'S'
        val isEnd = value == 'E'
        Vertex(
          x = x,
          y = y,
          pathLength = Int.MAX_VALUE,
          height = when {
            isStart -> 'a'
            isEnd -> 'z'
            else -> value
          } - 'a',
          isStart = isStart,
          isEnd = isEnd,
        )
      }
    }
}
