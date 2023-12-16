package aoc.year2023

import aoc.library.Direction
import aoc.library.Direction.Down
import aoc.library.Direction.Left
import aoc.library.Direction.Right
import aoc.library.Direction.Up
import aoc.library.Point
import aoc.library.Puzzle
import aoc.library.bounds
import aoc.library.move
import aoc.year2023.Day16.Tile.Floor
import aoc.year2023.Day16.Tile.HorizontalSplitter
import aoc.year2023.Day16.Tile.LeftMirror
import aoc.year2023.Day16.Tile.RightMirror
import aoc.year2023.Day16.Tile.VerticalSplitter

object Day16 : Puzzle<Int, Int>(day = 16) {

  override fun solvePart1(input: String): Int {
    val map = parse(input)
    return maximumEnergizedTiles(map, Point.Zero, Right)
  }

  override fun solvePart2(input: String): Int {
    val map = parse(input)
    val bounds = map.keys.bounds()
    return map
      .filterKeys {
        it.x == bounds.left || it.x == bounds.right || it.y == bounds.top || it.y == bounds.bottom
      }
      .mapValues { (point, _) ->
        buildList {
          if (point.x == bounds.left) add(Right)
          if (point.x == bounds.right) add(Left)
          if (point.y == bounds.top) add(Down)
          if (point.y == bounds.bottom) add(Up)
        }
      }
      .maxOf { (point, directions) ->
        directions.maxOf { direction ->
          maximumEnergizedTiles(map, point, direction)
        }
      }
  }

  private fun maximumEnergizedTiles(
    map: Map<Point, Tile>,
    startPoint: Point,
    startDirection: Direction,
  ): Int {
    val visited = mutableSetOf<Pair<Point, Direction>>()
    fun trackBeam(
      beam: Point,
      direction: Direction,
    ) {
      val currentTile = map[beam] ?: return
      if (!visited.add(beam to direction)) return

      when (currentTile) {
        Floor -> trackBeam(beam.move(direction), direction)
        VerticalSplitter -> {
          when (direction) {
            Left,
            Right,
            -> {
              trackBeam(beam.move(Up), Up)
              trackBeam(beam.move(Down), Down)
            }
            Up,
            Down,
            -> trackBeam(beam.move(direction), direction)
          }
        }
        HorizontalSplitter -> {
          when (direction) {
            Left,
            Right,
            -> trackBeam(beam.move(direction), direction)
            Up,
            Down,
            -> {
              trackBeam(beam.move(Left), Left)
              trackBeam(beam.move(Right), Right)
            }
          }
        }
        LeftMirror -> {
          val newDirection = when (direction) {
            Left -> Up
            Up -> Left
            Right -> Down
            Down -> Right
          }
          trackBeam(beam.move(newDirection), newDirection)
        }
        RightMirror -> {
          val newDirection = when (direction) {
            Left -> Down
            Up -> Right
            Right -> Up
            Down -> Left
          }
          trackBeam(beam.move(newDirection), newDirection)
        }
      }
    }

    trackBeam(startPoint, startDirection)

    return visited.map { it.first }.toSet().size
  }

  private enum class Tile(var char: Char) {
    Floor('.'),
    VerticalSplitter('|'),
    HorizontalSplitter('-'),
    LeftMirror('\\'),
    RightMirror('/'),
  }

  private fun parse(input: String): Map<Point, Tile> {
    val map = mutableMapOf<Point, Tile>()
    input.lines().forEachIndexed { y, line ->
      line.forEachIndexed { x, char ->
        map[Point(x, y)] = Tile.entries.first { it.char == char }
      }
    }
    return map
  }
}
