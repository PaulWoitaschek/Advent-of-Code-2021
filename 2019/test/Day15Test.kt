package aoc.year2019

import aoc.library.Point
import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day15Test {

  @Test
  fun part1() {
    Day15.solvePart1() shouldBeExactly 308
  }

  @Test
  fun part2() {
    Day15.solvePart2() shouldBeExactly 328
  }

  @Test
  fun oxygenFillTime() {
    val map = """
         ##
        #..##
        #.#..#
        #.O.#
         ###
    """.trimIndent().lines().flatMapIndexed { y, line ->
      line.mapIndexed { x, char ->
        Point(x, y) to when (char) {
          ' ', '.' -> Day15.Tile.Floor
          'O' -> Day15.Tile.Target
          '#' -> Day15.Tile.Wall
          else -> error("Invalid tile=$char")
        }
      }
    }.associate { it }
    Day15.oxygenFillTime(
      map,
    ) shouldBeExactly 4
  }
}
