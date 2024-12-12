package aoc.year2023

import aoc.library.Direction
import aoc.library.Point
import aoc.library.Puzzle
import aoc.library.Rect
import aoc.library.bounds
import aoc.library.move

object Day14 : Puzzle<Int, Int>(day = 14) {

  override fun solvePart1(input: String): Int = RockMap.parse(input).move(Direction.Up).weight()

  override fun solvePart2(input: String): Int {
    val visited = mutableSetOf<RockMap>()
    var current = RockMap.parse(input)
    val limit = 1000000000
    var i = 0L
    while (i++ < limit) {
      val moved = current.move(Direction.Up).move(Direction.Left).move(Direction.Down).move(Direction.Right)
      if (!visited.add(moved)) {
        val loopSize = visited.indexOf(moved) + 1 - i
        val remainingSteps = limit - i
        i += (remainingSteps / loopSize) * loopSize
      }
      current = moved
    }
    return current.weight()
  }

  private data class RockMap(private val tiles: Map<Point, Tile>, private val bounds: Rect) {

    fun move(direction: Direction): RockMap {
      val newTiles = mutableMapOf<Point, Tile>()
      tiles.forEach { (position, tile) ->
        when (tile) {
          Tile.Stone -> {
            val steps = generateSequence(position) { it.move(direction) }
              .drop(1)
              .takeWhile { it in bounds && tiles[it] != Tile.Wall }
              .count { tiles[it] == Tile.Floor || tiles[it] == null }
            val newPosition = position.move(direction = direction, steps = steps)
            newTiles[newPosition] = Tile.Stone
          }
          else -> newTiles[position] = tile
        }
      }
      return copy(tiles = newTiles)
    }

    fun weight(): Int = tiles.filterValues { it == Tile.Stone }.keys
      .sumOf { point ->
        bounds.height() + 1 - point.y
      }

    companion object {
      fun parse(input: String): RockMap {
        val tiles = mutableMapOf<Point, Tile>()
        input.lines().forEachIndexed { y, line ->
          line.forEachIndexed { x, char ->
            val tile = when (char) {
              '#' -> Tile.Wall
              'O' -> Tile.Stone
              else -> null
            }
            if (tile != null) {
              tiles[Point(x, y)] = tile
            }
          }
        }
        return RockMap(tiles, tiles.keys.bounds())
      }
    }
  }

  private enum class Tile {
    Stone,
    Wall,
    Floor,
  }
}
