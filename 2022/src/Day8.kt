package aoc.year2022

import aoc.library.Point
import aoc.library.Puzzle

object Day8 : Puzzle<Int, Int>(8) {
  override fun solvePart1(input: String) = Forest(input).visibleTreesFromOutsideTheGrid()
  override fun solvePart2(input: String) = Forest(input).highestScenicScore()
}

private class Forest(input: String) {

  private val trees: List<List<Int>> = input.lines().filter(String::isNotEmpty).map { line ->
    line.map { height -> height.digitToInt() }
  }
  private val forestWidth = trees.first().size
  private val forestHeight = trees.size
  private val allPoints = (0 until forestWidth).flatMap { x ->
    (0 until forestHeight).map { y -> Point(x, y) }
  }

  private fun Point.height() = trees[y][x]

  fun visibleTreesFromOutsideTheGrid(): Int = allPoints.count { point ->
    val pointHeight = point.height()
    point.diagonals().any { diagonalLine ->
      diagonalLine.all {
        it.height() < pointHeight
      }
    }
  }

  fun highestScenicScore(): Int = allPoints.maxOf { point ->
    val treeHeight = point.height()
    point.diagonals().map { diagonals ->
      if (diagonals.all { it.height() < treeHeight }) {
        diagonals.size
      } else {
        diagonals.takeWhile { it.height() < treeHeight }.count() + 1
      }
    }.reduce(Int::times)
  }

  private fun Point.diagonals() = listOf(
    (0 until x).map { Point(it, y) }.reversed(),
    (x + 1 until forestWidth).map { Point(it, y) },
    (0 until y).map { Point(x, it) }.reversed(),
    (y + 1 until forestHeight).map { Point(x, it) },
  )
}
