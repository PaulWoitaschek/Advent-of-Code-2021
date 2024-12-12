package aoc.year2022

import aoc.library.Point
import aoc.library.Puzzle

object Day14 : Puzzle<Int, Int>(14) {

  override fun solvePart1(input: String): Int {
    val cave = Cave.parse(input)
    val rockBottom: Int = cave.rocks.maxOf { it.y }
    while (true) {
      cave.advanceTime()
      if (cave.sands.any { it.y >= rockBottom }) {
        return cave.sands.size - 1
      }
    }
  }

  override fun solvePart2(input: String): Int {
    val cave = Cave.parse(input)
    val floor: Int = cave.rocks.maxOf { it.y } + 2
    while (true) {
      cave.advanceTime(floor = floor)
      if (!cave.advanceTime(floor = floor)) {
        return cave.sands.size
      }
    }
  }

  class Cave(val sands: MutableSet<Point>, val rocks: Set<Point>) {

    private var candidate: Point? = null

    fun advanceTime(floor: Int? = null): Boolean {
      val candidate = candidate
      if (candidate != null) {
        val y = candidate.y + 1
        val newPosition = sequenceOf(0, -1, 1).map { Point(candidate.x + it, y) }
          .firstOrNull {
            val belowFloor = if (floor == null) false else it.y >= floor
            !belowFloor && it !in sands && it !in rocks
          }
        if (newPosition != null) {
          sands.add(newPosition)
          sands.remove(candidate)
          this.candidate = newPosition
          return true
        }
      }

      this.candidate = SandOrigin
      return sands.add(SandOrigin)
    }

    companion object {
      val SandOrigin = Point(500, 0)

      fun parse(input: String): Cave {
        val rocks = input.lines().filter(String::isNotEmpty)
          .flatMap { line ->
            line.split(" -> ", ",").chunked(2)
              .map { (x, y) -> Point(x.toInt(), y.toInt()) }
              .zipWithNext { from, to ->
                if (from.y == to.y) {
                  (minOf(from.x, to.x)..maxOf(from.x, to.x)).map { Point(it, from.y) }
                } else {
                  (minOf(from.y, to.y)..maxOf(from.y, to.y)).map { Point(from.x, it) }
                }
              }
              .flatten()
          }

        return Cave(
          sands = mutableSetOf(),
          rocks = rocks.toSet(),
        )
      }
    }
  }
}
