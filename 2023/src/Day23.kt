package aoc.year2023

import aoc.library.Direction
import aoc.library.Point
import aoc.library.Puzzle
import aoc.library.grid
import aoc.library.move

object Day23 : Puzzle<Int, Int>(day = 23) {

  override fun solvePart1(input: String): Int {
    val grid = parse(input)
    return maxPath(
      forest = grid,
      start = grid.start,
      end = grid.end,
      canClimbSlopes = false,
      stopPoints = emptySet(),
    )
  }

  override fun solvePart2(input: String): Int {
    val grid = parse(input)
    val start = grid.start
    val end = grid.end
    val pointsOfInterest = pointsOfInterest(start, end, grid)
    val adjacentPointsOfInterest = adjacentPointsOfInterest(pointsOfInterest, grid)
    val distancesBetweenPointsOfInterest = distancesBetweenPointsOfInterest(
      adjacentPointsOfInterest = adjacentPointsOfInterest,
      grid = grid,
      pointsOfInterest = pointsOfInterest,
    )
    return maxWalk(paths = distancesBetweenPointsOfInterest, start = start, end = end)
  }

  private fun distancesBetweenPointsOfInterest(
    adjacentPointsOfInterest: Map<Point, Set<Point>>,
    grid: Map<Point, Tile>,
    pointsOfInterest: Set<Point>,
  ): Map<Point, Map<Point, Int>> = adjacentPointsOfInterest.mapValues { (from, other) ->
    other.associateWith { o ->
      maxPath(forest = grid, start = from, end = o, canClimbSlopes = true, stopPoints = pointsOfInterest - o)
    }
  }

  private fun adjacentPointsOfInterest(
    pointsOfInterest: Set<Point>,
    grid: Map<Point, Tile>,
  ): Map<Point, Set<Point>> {
    return pointsOfInterest.associateWith { poi ->
      val visited = mutableSetOf<Point>()
      val visitedPointsOfInterest = mutableSetOf<Point>()
      fun walk(from: Point) {
        if (!visited.add(from)) {
          return
        }
        from.adjacentOrthogonal().forEach { adjacent ->
          if (adjacent in pointsOfInterest) {
            visitedPointsOfInterest += adjacent
          } else {
            val tile = grid[adjacent]
            when (tile) {
              Tile.Path,
              is Tile.Slope,
              -> {
                walk(adjacent)
              }
              Tile.Forest,
              null,
              -> {
              }
            }
          }
        }
      }
      walk(poi)

      visitedPointsOfInterest.remove(poi)
      visitedPointsOfInterest
    }
  }

  private fun pointsOfInterest(
    start: Point,
    end: Point,
    grid: Map<Point, Tile>,
  ): Set<Point> {
    val pointsOfInterest = mutableSetOf<Point>()
    pointsOfInterest.add(start)
    pointsOfInterest.add(end)
    grid
      .filter { (_, tile) ->
        when (tile) {
          Tile.Forest -> false
          Tile.Path,
          is Tile.Slope,
          -> true
        }
      }
      .filter { (point, _) ->
        point.adjacentOrthogonal().count {
          when (grid[it]) {
            Tile.Forest -> false
            Tile.Path -> true
            is Tile.Slope -> true
            null -> false
          }
        } > 2
      }
      .forEach {
        pointsOfInterest.add(it.key)
      }
    return pointsOfInterest
  }

  private fun parse(input: String): Map<Point, Tile> = grid(input) { char ->
    when (char) {
      '#' -> Tile.Forest
      '.' -> Tile.Path
      else -> Tile.Slope(Direction.fromArrowOrNull(char)!!)
    }
  }

  private val Map<Point, Tile>.start: Point get() = filterValues { it is Tile.Path }.minBy { it.key.y }.key
  private val Map<Point, Tile>.end: Point get() = filterValues { it is Tile.Path }.maxBy { it.key.y }.key

  private fun maxWalk(
    paths: Map<Point, Map<Point, Int>>,
    start: Point,
    end: Point,
  ): Int {
    var max = 0
    val path = mutableListOf(start)
    fun walk(steps: Int) {
      val current = path.last()
      if (current == end) {
        max = maxOf(max, steps)
        return
      }
      paths[current]!!.forEach { (point, distance) ->
        if (point !in path) {
          path.add(point)
          walk(steps + distance)
          path.removeLast()
        }
      }
    }

    walk(0)

    return max
  }

  private fun maxPath(
    forest: Map<Point, Tile>,
    start: Point,
    end: Point,
    canClimbSlopes: Boolean,
    stopPoints: Set<Point>,
  ): Int {
    var max = 0
    fun walk(path: List<Point>) {
      val current = path.last()
      if (current == end) {
        max = maxOf(max, path.size - 1)
        return
      }
      if (!canClimbSlopes) {
        val currentTile = forest[current]!!
        if (currentTile is Tile.Slope) {
          val inSlopeDirection = current.move(currentTile.direction)
          if (inSlopeDirection != path[path.lastIndex - 1]) {
            walk(path + inSlopeDirection)
          }
          return
        }
      }
      current.adjacentOrthogonal()
        .forEach { adjacent ->
          if (adjacent in stopPoints) {
            return@forEach
          }
          if (adjacent !in path) {
            when (forest[adjacent]) {
              Tile.Path,
              is Tile.Slope,
              -> {
                walk(path + adjacent)
              }
              Tile.Forest, null -> {}
            }
          }
        }
    }

    walk(listOf(start))

    return max
  }

  private sealed interface Tile {
    data object Path : Tile
    data object Forest : Tile
    data class Slope(var direction: Direction) : Tile
  }
}
