package aoc.year2020

import aoc.library.Point
import aoc.library.Puzzle

object Day3 : Puzzle<Int, Int>(3) {

  override fun solvePart1(input: String): Int = solve(input, Point(3, 1))

  override fun solvePart2(input: String): Int = solve(
    input,
    Point(1, 1),
    Point(3, 1),
    Point(5, 1),
    Point(7, 1),
    Point(1, 2),
  )

  private fun solve(
    input: String,
    vararg slopes: Point,
  ): Int {
    val treeMap = parseInput(input)
    return slopes.map { countTreesOnSlope(treeMap, it) }.reduce(Int::times)
  }

  private fun countTreesOnSlope(
    treeMap: List<List<Boolean>>,
    slope: Point,
  ): Int = generateSequence(Point(0, 0)) { it + slope }
    .takeWhile { it.y < treeMap.size }
    .count { (x, y) ->
      val treeLine = treeMap[y]
      treeLine[x.mod(treeLine.size)]
    }

  private fun parseInput(input: String): List<List<Boolean>> = input.lines().filter(String::isNotEmpty)
    .map { treeLine ->
      treeLine.map { it == '#' }
    }
}
