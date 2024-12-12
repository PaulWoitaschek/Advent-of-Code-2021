package aoc.year2023

import aoc.library.Puzzle
import kotlin.math.sign

object Day24 : Puzzle<Int, Int>(day = 24) {

  override fun solvePart1(input: String): Int = solvePart1(input, 200000000000000..400000000000000)

  fun solvePart1(
    input: String,
    range: LongRange,
  ): Int {
    val doubleRange = range.first.toDouble()..range.last.toDouble()
    val hailStones = parse(input)
    var intersections = 0
    for (i in hailStones.indices) {
      for (j in i + 1 until hailStones.size) {
        val left = hailStones[i]
        val right = hailStones[j]
        val intersect = left.xyIntersection(right)
        if (intersect != null) {
          if (intersect.first in doubleRange && intersect.second in doubleRange) {
            intersections++
          }
        }
      }
    }
    return intersections
  }

  private fun parse(input: String): List<HailStone> {
    val digitRegex = """(-?\d+)""".toRegex()
    val hailStones = input.lines()
      .map { line ->
        val values = digitRegex.findAll(line).map { it.value.toLong() }.toList()
        HailStone(
          x = values[0],
          y = values[1],
          z = values[2],
          vx = values[3],
          vy = values[4],
          vz = values[5],
        )
      }
    return hailStones
  }

  private data class HailStone(val x: Long, val y: Long, val z: Long, val vx: Long, val vy: Long, val vz: Long) {

    private val slope = vy.toDouble() / vx
    private val b = y - slope * x

    fun xyIntersection(other: HailStone): Pair<Double, Double>? {
      if (slope == other.slope) {
        return null
      }
      val x = (other.b - b) / (slope - other.slope)
      val y = slope * x + b
      return Pair(x, y).takeIf { (x, _) ->
        (x - this.x).sign.toInt() == vx.sign && (x - other.x).sign.toInt() == other.vx.sign
      }
    }
  }

  override fun solvePart2(input: String): Int {
    TODO()
  }
}
