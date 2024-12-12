package aoc.year2024

import aoc.library.Direction
import aoc.library.Point
import aoc.library.Puzzle
import aoc.library.move

object Day6 : Puzzle<Int, Int>(day = 6) {

  override fun solvePart1(input: String): Int {
    val lab = ManufacturingLab.parse(input)
    return when (val walk = walk(lab)) {
      is WalkResult.ExitedNormally -> walk.visited.map { it.first }.toSet().size
      WalkResult.Looped -> error("Unexpected")
    }
  }

  override fun solvePart2(input: String): Int {
    val lab = ManufacturingLab.parse(input)
    return (0..lab.width)
      .flatMap { y ->
        (0..lab.height).map { x ->
          Point(x, y)
        }
      }
      .parallelStream()
      .filter { newObstruction ->
        isLoopingObstruction(newObstruction, lab)
      }
      .count().toInt()
  }

  private fun isLoopingObstruction(
    newObstruction: Point,
    lab: ManufacturingLab,
  ): Boolean {
    if (newObstruction in lab.obstructions) return false
    if (newObstruction == lab.position) return false
    val changedLab = lab.copy(obstructions = lab.obstructions + newObstruction)
    return when (walk(changedLab)) {
      is WalkResult.ExitedNormally -> false
      WalkResult.Looped -> true
    }
  }

  private fun walk(changedLab: ManufacturingLab): WalkResult {
    var position = changedLab.position
    var facing = changedLab.facing
    val visited = mutableSetOf<Pair<Point, Direction>>()
    while (true) {
      val nextPosition = position.move(facing)
      if (nextPosition !in changedLab) {
        return WalkResult.ExitedNormally(visited)
      } else if (nextPosition in changedLab.obstructions) {
        facing = facing.clockwise()
      } else {
        position = nextPosition
        if (!visited.add(nextPosition to facing)) {
          return WalkResult.Looped
        }
      }
    }
  }

  private sealed interface WalkResult {

    data object Looped : WalkResult

    data class ExitedNormally(val visited: Set<Pair<Point, Direction>>) : WalkResult
  }

  private data class ManufacturingLab(
    val obstructions: Set<Point>,
    val position: Point,
    val facing: Direction,
    val width: Int,
    val height: Int,
  ) {

    operator fun contains(point: Point): Boolean = point.x in 0 until width && point.y in 0 until height

    companion object {

      fun parse(input: String): ManufacturingLab {
        val obstructions = mutableSetOf<Point>()
        var position = Point(0, 0)
        var facing = Direction.Up
        val lines = input.lines()
        val width = lines.first().length
        val height = lines.size
        lines.forEachIndexed { y, line ->
          line.forEachIndexed { x, char ->
            if (char == '#') {
              obstructions += Point(x, y)
            } else {
              val direction = Direction.fromArrowOrNull(char)
              if (direction != null) {
                facing = direction
                position = Point(x, y)
              }
            }
          }
        }
        return ManufacturingLab(
          obstructions = obstructions,
          facing = facing,
          width = width,
          height = height,
          position = position,
        )
      }
    }
  }
}
