package aoc.year2022

import aoc.library.Point
import aoc.library.Puzzle
import java.util.BitSet

object Day17 : Puzzle<Long, Nothing>(17) {

  private const val REPORT_ON_ROCK = 2022L

  override fun solvePart2(input: String): Nothing = error("Nada")

  override fun solvePart1(input: String): Long {
    val cave = Cave()

    data class Rock(val shape: RockShape, val coordinates: List<Point>)

    fun newRock(shape: RockShape): Rock {
      val top = cave.top() + 4
      return Rock(shape, shape.points.map { it.copy(x = it.x + 2, y = it.y + top) })
    }

    var rock = newRock(RockShape.A)

    fun moveRock(direction: Direction): Boolean {
      val newCoordinates = rock.coordinates.map {
        it.copy(
          x = it.x + when (direction) {
            Direction.Left -> -1
            Direction.Right -> +1
            Direction.Down -> 0
          },
          y = it.y + when (direction) {
            Direction.Left,
            Direction.Right,
            -> 0
            Direction.Down -> -1
          },
        )
      }
      val inBonds = newCoordinates.all { (x, y) ->
        x in 0 until Cave.WIDTH && y > 0 && !cave.hasRock(x, y)
      }
      return if (inBonds) {
        rock = rock.copy(coordinates = newCoordinates)
        true
      } else {
        false
      }
    }

    var rocks = 0L

    jetStream(input).forEach { jetPush ->
      moveRock(jetPush)

      if (!moveRock(Direction.Down)) {
        if (rocks == REPORT_ON_ROCK) {
          return cave.top().toLong()
        }
        rocks++
        rock.coordinates.forEach { (x, y) ->
          cave.putRock(x, y)
        }
        rock = newRock(rock.shape.next())
      }
    }
    error("No answer found")
  }

  private fun jetStream(input: String): Sequence<Direction> {
    val jetPattern = input.map {
      when (it) {
        '>' -> Direction.Right
        '<' -> Direction.Left
        else -> error("Invalid directions")
      }
    }
    return generateSequence { jetPattern }.flatten()
  }

  enum class Direction { Left, Right, Down }
  enum class RockShape(shapeString: String) {
    A("####"),
    B(
      """
    .#.
    ###
    .#.
      """.trimIndent(),
    ),
    C(
      """
    ..#
    ..#
    ###
      """.trimIndent(),
    ),
    D(
      """
    #
    #
    #
    #
      """.trimIndent(),
    ),
    E(
      """
    ##
    ##
      """.trimIndent(),
    ),
    ;

    fun next(): RockShape = entries.getOrNull(ordinal + 1) ?: A

    val points = shapeString.lines().reversed().flatMapIndexed { y, line ->
      line.mapIndexedNotNull { x, char ->
        if (char == '#') {
          Point(x, y)
        } else {
          null
        }
      }
    }
  }

  class Cave {

    private val value = BitSet()

    fun top(): Int = (value.length() - 2) / WIDTH

    fun putRock(
      x: Int,
      y: Int,
    ) {
      value.set((y * WIDTH + x) + 1)
    }

    fun hasRock(
      x: Int,
      y: Int,
    ): Boolean = value.get((y * WIDTH + x) + 1)

    companion object {
      const val WIDTH = 7
    }
  }
}
