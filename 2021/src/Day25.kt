package aoc.year2021

import aoc.library.Puzzle

object Day25 : Puzzle<Int, Int>(25) {

  override fun solvePart1(input: String): Int = sequence {
    var current = parse(input)
    yield(current)
    while (true) {
      val next = current.move()
      if (next == current) {
        break
      } else {
        yield(next)
        current = next
      }
    }
  }.count()

  override fun solvePart2(input: String) = error("No part 2")
}

private enum class Cucumber { Right, Down, Empty }

private typealias CucumberMap = List<List<Cucumber>>

private fun parse(input: String): CucumberMap = input.lines()
  .map { line ->
    line.map { direction ->
      when (direction) {
        'v' -> Cucumber.Down
        '>' -> Cucumber.Right
        '.' -> Cucumber.Empty
        else -> error("Invalid direction $direction")
      }
    }
  }

private fun CucumberMap.move(): CucumberMap = moveRight().moveDown()

private fun CucumberMap.moveRight(): CucumberMap {
  val result = toMutableList().map { it.toMutableList() }
  val width = result.first().size
  indices.forEach { y ->
    first().indices.forEach { x ->
      if (this[y][x] == Cucumber.Right) {
        val targetX = if (x == width - 1) 0 else x + 1
        if (this[y][targetX] == Cucumber.Empty) {
          result[y][targetX] = Cucumber.Right
          result[y][x] = Cucumber.Empty
        }
      }
    }
  }
  return result
}

private fun CucumberMap.moveDown(): CucumberMap {
  val result = toMutableList().map { it.toMutableList() }
  val height = result.size
  indices.forEach { y ->
    first().indices.forEach { x ->
      if (this[y][x] == Cucumber.Down) {
        val targetY = if (y == height - 1) 0 else y + 1
        if (this[targetY][x] == Cucumber.Empty) {
          result[targetY][x] = Cucumber.Down
          result[y][x] = Cucumber.Empty
        }
      }
    }
  }
  return result
}
