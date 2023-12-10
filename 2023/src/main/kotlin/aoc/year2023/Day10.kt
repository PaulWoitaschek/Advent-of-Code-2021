package aoc.year2023

import aoc.library.Direction
import aoc.library.Point
import aoc.library.Puzzle
import aoc.library.move

object Day10 : Puzzle<Long, Long>(year = 2023, day = 10) {

  override fun solvePart1(input: String): Long {
    return (findLoop(parse(input)).size / 2).toLong()
  }

  private fun findLoop(map: Map<Point, Tile>): List<Point> {
    val start = map.entries.first { it.value == Tile.Start }.key
    fun tile(point: Point) = map[point] ?: Tile.Ground
    tailrec fun path(path: List<Point>): List<Point>? {
      val position = path.last()
      val next = tile(position).connecting
        .map { direction -> position.move(direction, false) }
        .singleOrNull { it != path.getOrNull(path.lastIndex - 1) }
      return when (next) {
        null -> null
        start -> path
        else -> path(path + next)
      }
    }
    return start.adjacent().firstNotNullOf { path(listOf(start, it)) }
  }

  override fun solvePart2(input: String): Long {
    TODO()
  }

  private fun parse(input: String): Map<Point, Tile> {
    val map = mutableMapOf<Point, Tile>()
    input.lines().flatMapIndexed { y, line ->
      line.mapIndexed { x, symbol ->
        map[Point(x, y)] = Tile.bySymbol.getValue(symbol)
      }
    }
    return map
  }

  private enum class Tile(val symbol: Char, val connecting: List<Direction>) {
    Vertical('|', listOf(Direction.Up, Direction.Down)),
    Horizontal('-', listOf(Direction.Left, Direction.Right)),
    NorthEast('L', listOf(Direction.Up, Direction.Right)),
    NorthWest('J', listOf(Direction.Up, Direction.Left)),
    SouthWest('7', listOf(Direction.Down, Direction.Left)),
    SouthEast('F', listOf(Direction.Down, Direction.Right)),
    Ground('.', emptyList()),
    Start('S', Direction.entries),
    ;

    companion object {
      val bySymbol = Tile.entries.associateBy { it.symbol }
    }
  }
}
