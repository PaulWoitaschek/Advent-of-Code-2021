package aoc.year2019

import aoc.library.Direction
import aoc.library.Point
import aoc.library.Puzzle
import aoc.library.move
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object Day15 : Puzzle<Int, Int>(15) {

  override fun solvePart1(input: String): Int = findShortestPath(extractMap(input))
  override fun solvePart2(input: String): Int = oxygenFillTime(extractMap(input))

  private fun extractMap(input: String): Map<Point, Tile> = runBlocking {
    val inputs = Channel<Long>()
    suspend fun send(direction: Direction) {
      inputs.send(
        when (direction) {
          Direction.Left -> 3
          Direction.Up -> 1
          Direction.Right -> 4
          Direction.Down -> 2
        },
      )
    }

    val map = mutableMapOf(Point.Zero to Tile.Floor)
    val computer = IntCodeComputer(input.split(",").map(String::toLong), inputs)
    val computerProcessing = launch {
      computer.run()
    }

    suspend fun walk(point: Point) {
      Direction.entries
        .forEach { direction ->
          val newPosition = point.move(direction)
          if (newPosition !in map) {
            send(direction)
            when (val status = computer.outputs.receive().toInt()) {
              0 -> {
                map[newPosition] = Tile.Wall
              }
              1, 2 -> {
                map[newPosition] = if (status == 1) Tile.Floor else Tile.Target
                walk(newPosition)
                val oppositeDuration = point.directionTo(newPosition)
                send(oppositeDuration)
                computer.outputs.receive()
              }
              else -> error("nope")
            }
          }
        }
    }
    walk(Point.Zero)
    computerProcessing.cancel()
    map
  }

  private fun Point.directionTo(other: Point): Direction = when {
    other.x > x -> Direction.Left
    other.x < x -> Direction.Right
    other.y > y -> Direction.Up
    other.y < y -> Direction.Down
    else -> error("nope")
  }

  private fun findShortestPath(map: Map<Point, Tile>): Int {
    var min = Int.MAX_VALUE
    fun dfs(
      position: Point,
      visited: Set<Point>,
      length: Int,
    ) {
      if (length > min) return
      position.adjacentOrthogonal()
        .filter { it !in visited }
        .forEach { neighbor ->
          val value = map.getValue(neighbor)
          when (value) {
            Tile.Wall -> {}
            Tile.Floor -> {
              dfs(
                position = neighbor,
                visited = visited + neighbor,
                length = length + 1,
              )
            }
            Tile.Target -> {
              min = minOf(min, length + 1)
            }
          }
        }
    }
    dfs(Point.Zero, setOf(Point.Zero), 0)
    return min
  }

  enum class Tile {
    Wall,
    Floor,
    Target,
  }

  fun oxygenFillTime(map: Map<Point, Tile>): Int {
    val startPoint = map.firstNotNullOf { (point, tile) ->
      if (tile == Tile.Target) point else null
    }

    val oxygen = mutableSetOf(startPoint)
    var minute = 0
    while (true) {
      val new = oxygen
        .flatMap { it.adjacentOrthogonal() }
        .filter { it !in oxygen && map[it] != Tile.Wall }
        .toSet()
      if (!oxygen.addAll(new)) {
        return minute
      }
      minute++
    }
  }
}
