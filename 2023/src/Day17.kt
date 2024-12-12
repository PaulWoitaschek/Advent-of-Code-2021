package aoc.year2023

import aoc.library.Direction
import aoc.library.Point
import aoc.library.Puzzle
import aoc.library.bounds
import aoc.library.move
import java.util.PriorityQueue

object Day17 : Puzzle<Int, Int>(day = 17) {

  override fun solvePart1(input: String): Int = solve(input = input, stepsAfterTurn = 1, maxTurns = 3)

  override fun solvePart2(input: String): Int = solve(input = input, stepsAfterTurn = 4, maxTurns = 10)

  private fun solve(
    input: String,
    stepsAfterTurn: Int,
    maxTurns: Int,
  ): Int {
    val map = mutableMapOf<Point, Int>()
    input.lines().forEachIndexed { y, line ->
      line.forEachIndexed { x, char ->
        map[Point(x, y)] = char.digitToInt()
      }
    }
    val target = map.keys.maxBy { it.manhattanDistanceTo(Point.Zero) }
    val bounds = map.keys.bounds()
    val queue = PriorityQueue<FlowWithHeat>(compareBy { it.heat })
    queue.add(
      FlowWithHeat(
        flow = Flow(Point.Zero, Direction.Right, 0),
        heat = 0,
      ),
    )

    val visited = mutableSetOf<Flow>()
    while (queue.isNotEmpty()) {
      val (flow, heat) = queue.remove()

      if (flow.position == target) {
        return heat
      }

      if (!visited.add(flow)) {
        continue
      }

      if (flow.stepsInDirection < maxTurns) {
        val newPosition = flow.position.move(flow.direction)
        if (newPosition in bounds) {
          queue.add(
            FlowWithHeat(
              flow = Flow(
                position = newPosition,
                direction = flow.direction,
                stepsInDirection = flow.stepsInDirection + 1,
              ),
              heat = heat + map[newPosition]!!,
            ),
          )
        }
      }

      fun addForTurn(direction: Direction) {
        var newPosition = flow.position
        var newHeat = heat
        repeat(stepsAfterTurn) {
          newPosition = newPosition.move(direction)
          newHeat += map[newPosition] ?: return
        }
        queue.add(
          FlowWithHeat(
            flow = Flow(
              position = newPosition,
              direction = direction,
              stepsInDirection = stepsAfterTurn,
            ),
            heat = newHeat,
          ),
        )
      }

      addForTurn(flow.direction.clockwise())
      addForTurn(flow.direction.counterClockwise())
    }
    error("Invalid data")
  }

  private data class Flow(val position: Point, val direction: Direction, val stepsInDirection: Int)

  private data class FlowWithHeat(val flow: Flow, val heat: Int)
}
