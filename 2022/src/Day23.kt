package aoc.year2022

import aoc.library.Point
import aoc.library.Puzzle

object Day23 : Puzzle<Int, Int>(23) {

  override fun solvePart1(input: String): Int {
    val elves = parseInput(input)

    var direction = Direction.North

    repeat(10) {
      simulateRound(elves, direction)
      direction = direction.next()
    }

    val width = (elves.maxOf { it.position.x } - elves.minOf { it.position.x }) + 1
    val height = (elves.maxOf { it.position.y } - elves.minOf { it.position.y }) + 1
    val rectSize = width * height

    return rectSize - elves.size
  }

  override fun solvePart2(input: String): Int {
    val elves = parseInput(input)

    var direction = Direction.North
    var previousPositions: List<Point> = emptyList()
    var round = 0
    while (true) {
      round++
      simulateRound(elves, direction)
      val positions = elves.map { it.position }
      if (previousPositions == positions) {
        return round
      }
      previousPositions = positions
      direction = direction.next()
    }
  }

  private fun simulateRound(
    elves: List<Elf>,
    direction: Direction,
  ) {
    val candidates = elves.filter { elf ->
      elf.position.adjacent().any { adjacentPosition ->
        elves.any { it.position == adjacentPosition }
      }
    }
    val proposedPosition = candidates.groupBy { elf ->
      elf.proposePositions(direction)
        .firstOrNull { proposedPositions ->
          proposedPositions.all { positionLookingAt ->
            elves.none { otherElf ->
              positionLookingAt == otherElf.position
            }
          }
        }
        ?.first()
    }
    proposedPosition.forEach { (point, proposers) ->
      val singleProposer = proposers.singleOrNull()
      if (point != null && singleProposer != null) {
        proposers.single().position = point
      }
    }
  }

  private fun parseInput(input: String): List<Elf> {
    val elves = mutableListOf<Elf>()
    input.lines().forEachIndexed { y, line ->
      line.forEachIndexed { x, char ->
        if (char == '#') {
          elves.add(Elf(position = Point(x, y)))
        }
      }
    }
    return elves
  }

  enum class Direction {
    North,
    South,
    West,
    East,
    ;

    fun next(): Direction = entries.getOrNull(ordinal + 1) ?: entries.first()
  }

  data class Elf(var position: Point) {

    fun proposePositions(direction: Direction): Sequence<List<Point>> = sequence {
      var nextDirection = direction
      while (true) {
        yield(nextDirection)
        nextDirection = nextDirection.next()
      }
    }.map {
      val (x, y) = position
      when (it) {
        Direction.North -> listOf(
          Point(x, y - 1),
          Point(x - 1, y - 1),
          Point(x + 1, y - 1),
        )
        Direction.South -> listOf(
          Point(x, y + 1),
          Point(x - 1, y + 1),
          Point(x + 1, y + 1),
        )
        Direction.West -> listOf(
          Point(x - 1, y),
          Point(x - 1, y + 1),
          Point(x - 1, y - 1),
        )
        Direction.East -> listOf(
          Point(x + 1, y),
          Point(x + 1, y + 1),
          Point(x + 1, y - 1),
        )
      }
    }
      .take(4)
  }
}
