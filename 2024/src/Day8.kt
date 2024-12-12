package aoc.year2024

import aoc.library.Point
import aoc.library.Puzzle

object Day8 : Puzzle<Int, Int>(day = 8) {

  override fun solvePart1(input: String): Int = solve(input, accountForResonance = false)

  override fun solvePart2(input: String): Int = solve(input, accountForResonance = true)

  private fun solve(
    input: String,
    accountForResonance: Boolean,
  ): Int {
    val antennaMap = AntennaMap.parse(input)
    val antiNodes = mutableSetOf<Point>()
    antennaMap.groupedByFrequency.forEach { (_, antennas) ->
      antennas.forEachIndexed { a, first ->
        antennas.forEachIndexed { b, second ->
          if (a != b) {
            val distance = second - first
            if (accountForResonance) {
              antiNodes += generateSequence(second) { it + distance }
                .takeWhile { it in antennaMap }
            } else {
              val point = second + distance
              if (point in antennaMap) {
                antiNodes += point
              }
            }
          }
        }
      }
    }
    return antiNodes.count()
  }

  private data class AntennaMap(val groupedByFrequency: Map<Char, List<Point>>, private val width: Int, private val height: Int) {

    operator fun contains(point: Point): Boolean = point.x in 0 until width && point.y in 0 until height

    companion object {
      fun parse(input: String): AntennaMap {
        val allAntennas = mutableMapOf<Point, Char>()
        val lines = input.lines()
        lines.forEachIndexed { y, line ->
          line.forEachIndexed { x, char ->
            if (char != '.') {
              allAntennas[Point(x, y)] = char
            }
          }
        }
        val groupedByFrequency = allAntennas.toList()
          .groupBy { it.second }
          .mapValues { (_, entries) -> entries.map { it.first } }

        return AntennaMap(
          groupedByFrequency = groupedByFrequency,
          width = lines.first().length,
          height = lines.size,
        )
      }
    }
  }
}
