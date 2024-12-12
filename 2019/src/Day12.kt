package aoc.year2019

import aoc.library.Point3
import aoc.library.Puzzle
import aoc.library.lcm
import kotlin.math.absoluteValue

object Day12 : Puzzle<Int, Long>(12) {

  override fun solvePart1(input: String): Int {
    val moons = moonPositions(input).map(::Moon)
    repeat(1000) {
      runStep(moons)
    }
    return moons.sumOf { moon ->
      listOf(moon.position.x, moon.position.y, moon.position.z).sumOf { it.absoluteValue } *
        listOf(moon.velocity.x, moon.velocity.y, moon.velocity.z).sumOf { it.absoluteValue }
    }
  }

  override fun solvePart2(input: String): Long {
    val moons = moonPositions(input).map(::Moon)
    var step = 0L
    val stabilized = mutableMapOf<Point3.Axis, Long>()
    while (true) {
      step++
      runStep(moons)

      Point3.Axis.entries.forEach { axis ->
        if (axis !in stabilized) {
          if (moons.all { it.isStable(axis) }) {
            stabilized[axis] = step
          }
        }
      }

      if (stabilized.keys.containsAll(Point3.Axis.entries)) {
        var lcm = stabilized.values.first()
        stabilized.values.forEach {
          lcm = lcm(lcm, it)
        }
        return lcm
      }
    }
  }

  private fun runStep(moons: List<Moon>) {
    moons.forEach { left ->
      moons.forEach { right ->
        if (left != right) {
          left.velocity += (right.position - left.position).sign()
        }
      }
    }
    moons.forEach {
      it.position += it.velocity
    }
  }

  private fun moonPositions(input: String): List<Point3> = input.lines()
    .map { value ->
      val (x, y, z) = "<x=(.*?), y=(.*?), z=(.*?)>"
        .toRegex()
        .find(value)!!
        .destructured.toList()
        .map(String::toInt)
      Point3(x, y, z)
    }
}

private data class Moon(val originalPosition: Point3, var position: Point3, var velocity: Point3) {

  constructor(originalPosition: Point3) : this(originalPosition, originalPosition, Point3.Zero)

  fun isStable(axis: Point3.Axis): Boolean = velocity[axis] == 0 && position[axis] == originalPosition[axis]
}
