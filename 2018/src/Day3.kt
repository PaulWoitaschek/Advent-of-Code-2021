package aoc.year2018

import aoc.library.Point
import aoc.library.Puzzle
import kotlin.math.max
import kotlin.math.min

object Day3 : Puzzle<Int, Int>(3) {

  override fun solvePart1(input: String): Int = input.lines()
    .map(Claim::parse)
    .flatMap(Claim::points)
    .groupingBy { it }
    .eachCount()
    .count { it.value > 1 }

  override fun solvePart2(input: String): Int {
    val claims = input.lines().map(Claim::parse)
    return claims.single { candidate ->
      claims.filter { it != candidate }
        .none {
          it.overlaps(candidate)
        }
    }.id
  }

  data class Claim(val id: Int, val left: Int, val top: Int, val width: Int, val height: Int) {

    fun points(): List<Point> = (left..<right).flatMap { x ->
      (top..<bottom).map { y ->
        Point(x, y)
      }
    }

    private val right = left + width
    private val bottom = top + height

    fun overlaps(other: Claim): Boolean {
      val left = max(left, other.left)
      val right = min(right, other.right)
      val top = max(top, other.top)
      val bottom = min(bottom, other.bottom)
      return left < right && top < bottom
    }

    companion object {
      private val regex = """#(\d+) @ (\d+),(\d+): (\d+)x(\d+)""".toRegex()
      fun parse(input: String): Claim {
        val (id, x, y, width, height) = regex.find(input)!!.destructured.toList().map { it.toInt() }
        return Claim(
          id = id,
          top = y,
          left = x,
          width = width,
          height = height,
        )
      }
    }
  }
}
