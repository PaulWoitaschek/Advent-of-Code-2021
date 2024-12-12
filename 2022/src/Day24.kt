package aoc.year2022

import aoc.library.Direction
import aoc.library.Point
import aoc.library.Puzzle
import java.util.PriorityQueue

object Day24 : Puzzle<Int, Int>(24) {

  override fun solvePart1(input: String): Int = solve(input, part2 = false)

  override fun solvePart2(input: String): Int = solve(input, part2 = true)

  private fun solve(
    input: String,
    part2: Boolean,
  ): Int {
    val world = World.parse(input)

    val queue = PriorityQueue<Journey>(
      compareBy(
        { it.minute },
        { it.targets.size },
        { it.position.manhattanDistanceTo(it.targets.first()) },
      ),
    )

    queue.add(
      Journey(
        minute = 1,
        position = world.start,
        targets = if (part2) {
          listOf(world.end, world.start, world.end)
        } else {
          listOf(world.end)
        },
      ),
    )

    val visited = mutableSetOf<Journey>()

    var fastestPath = Int.MAX_VALUE

    val distanceFromStartToEnd = world.start.manhattanDistanceTo(world.end)

    while (queue.isNotEmpty()) {
      val journey = queue.remove()

      if (journey.minute >= fastestPath) {
        continue
      }

      val quickestPossiblePath =
        journey.targets.first().manhattanDistanceTo(journey.position) + (journey.targets.size - 1) * distanceFromStartToEnd
      if (journey.minute + quickestPossiblePath >= fastestPath) {
        continue
      }
      if (journey.position == journey.targets.singleOrNull()) {
        fastestPath = minOf(fastestPath, journey.minute)
        continue
      }

      val position = journey.position
      val targets = if (journey.position == journey.targets.first()) {
        journey.targets.drop(1)
      } else {
        journey.targets
      }
      listOf(
        position,
        position.copy(x = position.x - 1),
        position.copy(x = position.x + 1),
        position.copy(y = position.y - 1),
        position.copy(y = position.y + 1),
      ).filter { newPosition ->
        newPosition == world.start ||
          newPosition == world.end ||
          (
            newPosition.x in 0 until world.width &&
              (newPosition.y in 0 until world.height) &&
              !world.hasBlizzardAtMinute(
                newPosition,
                journey.minute + 1,
              )
            )
      }.forEach { moveTo ->
        val newJourney = Journey(
          minute = journey.minute + 1,
          position = moveTo,
          targets = targets,
        )
        if (visited.add(newJourney)) {
          queue.add(newJourney)
        }
      }
    }

    return fastestPath
  }

  data class Journey(val minute: Int, val position: Point, val targets: List<Point>)

  data class World(val width: Int, val height: Int, val blizzards: List<Blizzard>, val start: Point, val end: Point) {

    fun hasBlizzardAtMinute(
      point: Point,
      minute: Int,
    ): Boolean = blizzards.any { blizzard ->
      val blizzardPosition = blizzard.point
      when (blizzard.direction) {
        Direction.Left -> point.y == blizzardPosition.y && point.x == (blizzardPosition.x - minute).mod(width)
        Direction.Right -> point.y == blizzardPosition.y && point.x == (blizzardPosition.x + minute).mod(width)
        Direction.Up -> point.x == blizzardPosition.x && point.y == (blizzardPosition.y - minute).mod(height)
        Direction.Down -> point.x == blizzardPosition.x && point.y == (blizzardPosition.y + minute).mod(height)
      }
    }

    companion object {
      fun parse(input: String): World {
        val lines = input.lines().filter { it.isNotEmpty() }
        val width = lines.first().length - 2
        val height = lines.size - 2

        val blizzards = mutableListOf<Blizzard>()
        lines.drop(1)
          .dropLast(1)
          .forEachIndexed { y, line ->
            line.drop(1).dropLast(1)
              .forEachIndexed { x, c ->
                val direction = Direction.fromArrowOrNull(c)
                if (direction != null) {
                  blizzards += Blizzard(Point(x, y), direction)
                }
              }
          }

        val start = Point(0, -1)
        val end = Point(width - 1, height)
        return World(
          width = width,
          height = height,
          blizzards = blizzards,
          start = start,
          end = end,
        )
      }
    }
  }

  data class Blizzard(val point: Point, val direction: Direction)
}
