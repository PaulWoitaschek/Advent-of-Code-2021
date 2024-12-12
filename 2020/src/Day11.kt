package aoc.year2020

import aoc.library.Point
import aoc.library.Puzzle

private typealias SeatMap = List<List<Day11.Tile>>

object Day11 : Puzzle<Int, Int>(11) {

  override fun solvePart1(input: String): Int = solve(input, onlyConsiderNeighborSeats = true, emptyThreshold = 4)

  override fun solvePart2(input: String): Int = solve(input, onlyConsiderNeighborSeats = false, emptyThreshold = 5)

  private fun solve(
    input: String,
    onlyConsiderNeighborSeats: Boolean,
    emptyThreshold: Int,
  ): Int {
    val seats = parseInput(input)
    val adjacentSeats = adjacentSeats(seats, onlyConsiderNeighborSeats)
    return generateSequence(seats) { it.afterOneRound(adjacentSeats, emptyThreshold) }
      .zipWithNext()
      .takeWhile { it.first != it.second }
      .last()
      .second
      .sumOf { row ->
        row.count { it == Tile.OccupiedSeat }
      }
  }

  private fun adjacentSeats(
    seats: SeatMap,
    onlyConsiderNeighborSeats: Boolean,
  ): MutableMap<Point, List<Point>> {
    fun tile(point: Point): Tile = seats[point.y][point.x]
    val adjacentSeats = mutableMapOf<Point, List<Point>>()
    val width = seats.first().size
    val height = seats.size
    fun Point.onSeatMap(): Boolean = x in 0 until width && y in 0 until height
    seats.forEachIndexed { y, row ->
      row.forEachIndexed { x, tile ->
        if (tile != Tile.Floor) {
          val point = Point(x, y)
          adjacentSeats[point] = if (onlyConsiderNeighborSeats) {
            point.adjacent()
              .filter {
                it.onSeatMap() && tile(it) != Tile.Floor
              }
          } else {
            listOf(
              Point(1, 0),
              Point(1, 1),
              Point(1, -1),
              Point(0, 1),
              Point(0, -1),
              Point(-1, 0),
              Point(-1, 1),
              Point(-1, -1),
            ).mapNotNull { direction ->
              generateSequence(point) { it + direction }
                .drop(1).takeWhile {
                  it.onSeatMap()
                }.firstOrNull {
                  tile(it) != Tile.Floor
                }
            }
          }
        }
      }
    }
    return adjacentSeats
  }

  private fun SeatMap.afterOneRound(
    adjacentSeats: Map<Point, List<Point>>,
    emptyTreshold: Int,
  ): SeatMap {
    fun tile(point: Point): Tile = get(point.y)[point.x]
    return mapIndexed { y, row ->
      row.mapIndexed { x, tile ->
        when (tile) {
          Tile.Floor -> tile
          Tile.EmptySeat -> {
            if (adjacentSeats[Point(x, y)]!!.none { tile(it) == Tile.OccupiedSeat }) {
              Tile.OccupiedSeat
            } else {
              tile
            }
          }
          Tile.OccupiedSeat -> {
            if (adjacentSeats[Point(x, y)]!!.count { tile(it) == Tile.OccupiedSeat } >= emptyTreshold) {
              Tile.EmptySeat
            } else {
              tile
            }
          }
        }
      }
    }
  }

  private fun parseInput(input: String): SeatMap = input.lines().filter(String::isNotEmpty)
    .map { row ->
      row.map {
        when (it) {
          'L' -> Tile.EmptySeat
          '#' -> Tile.OccupiedSeat
          '.' -> Tile.Floor
          else -> error("Wrong input $it")
        }
      }
    }

  enum class Tile {
    Floor,
    EmptySeat,
    OccupiedSeat,
  }
}
