package aoc.year2022

import aoc.library.Direction
import aoc.library.Point
import aoc.library.Puzzle
import aoc.library.move
import kotlin.math.absoluteValue
import kotlin.math.sign

object Day9 : Puzzle<Int, Int>(9) {
  override fun solvePart1(input: String) = solve(input, knots = 2)
  override fun solvePart2(input: String) = solve(input, knots = 10)
}

private fun solve(
  input: String,
  knots: Int,
): Int {
  var head = Point(0, 0)
  var tail = List(knots - 1) { head }
  val visitedPoints = mutableSetOf<Point>()
  parseInput(input).forEach { direction ->
    head = head.move(direction)
    tail = tail.scan(head) { previous, current -> previous.follow(current) }.drop(1)
    visitedPoints.add(tail.last())
  }
  return visitedPoints.size
}

private fun Point.follow(current: Point): Point {
  val xDiff = x - current.x
  val yDiff = y - current.y
  return if (xDiff.absoluteValue == 2 || yDiff.absoluteValue == 2) {
    Point(current.x + xDiff.sign, current.y + yDiff.sign)
  } else {
    current
  }
}

private fun parseInput(input: String): List<Direction> = input.lines().filter { it.isNotEmpty() }
  .flatMap {
    val direction = Direction.parse(it.first())
    List(it.drop(2).toInt()) {
      direction
    }
  }
