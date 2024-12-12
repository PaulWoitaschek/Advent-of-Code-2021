package aoc.year2021

import aoc.library.Puzzle
import java.util.PriorityQueue

object Day15 : Puzzle<Int, Int>(15) {

  data class Vertex(val x: Int, val y: Int, var totalRisk: Int, val riskLevel: Int)

  override fun solvePart1(input: String): Int {
    val vertexes = input.lines()
      .mapIndexed { y, line ->
        line.mapIndexed { x, value ->
          Vertex(x = x, y = y, totalRisk = Int.MAX_VALUE, riskLevel = value.digitToInt())
        }
      }

    fun vertex(
      x: Int,
      y: Int,
    ): Vertex? = vertexes.getOrNull(y)?.getOrNull(x)

    fun Vertex.neighbors() = listOfNotNull(
      vertex(x - 1, y),
      vertex(x + 1, y),
      vertex(x, y - 1),
      vertex(x, y + 1),
    )

    val start = vertexes.first().first()
    val end = vertexes.last().last()

    start.totalRisk = 0

    val queue = PriorityQueue<Vertex>(compareBy { it.totalRisk })
    queue.add(start)

    while (true) {
      val vertex = queue.first()
      if (vertex == end) {
        return end.totalRisk
      }
      queue.remove(vertex)
      vertex.neighbors().forEach {
        if (it.totalRisk == Int.MAX_VALUE) {
          it.totalRisk = vertex.totalRisk + it.riskLevel
          queue.add(it)
        }
      }
    }
  }

  override fun solvePart2(input: String): Int {
    TODO()
  }
}
