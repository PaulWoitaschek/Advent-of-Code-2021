package aoc.year2020

import aoc.library.Direction
import aoc.library.Point
import aoc.library.Puzzle

object Day12 : Puzzle<Int, Int>(12) {

  override fun solvePart1(input: String): Int {
    val instructions = parseInstructions(input)

    var position = Point(0, 0)
    var facing = Direction.Right

    instructions.forEach { instruction ->
      when (instruction.action) {
        Instruction.Action.North -> {
          position = position.copy(y = position.y - instruction.count)
        }
        Instruction.Action.East -> {
          position = position.copy(x = position.x + instruction.count)
        }
        Instruction.Action.South -> {
          position = position.copy(y = position.y + instruction.count)
        }
        Instruction.Action.West -> {
          position = position.copy(x = position.x - instruction.count)
        }
        Instruction.Action.Left -> {
          repeat(instruction.count / 90) {
            facing = facing.counterClockwise()
          }
        }
        Instruction.Action.Right -> {
          repeat(instruction.count / 90) {
            facing = facing.clockwise()
          }
        }
        Instruction.Action.Forward -> {
          position = Point(
            x = position.x + when (facing) {
              Direction.Left -> -instruction.count
              Direction.Right -> instruction.count
              Direction.Up,
              Direction.Down,
              -> 0
            },
            y = position.y + when (facing) {
              Direction.Left,
              Direction.Right,
              -> 0
              Direction.Up -> -instruction.count
              Direction.Down -> instruction.count
            },
          )
        }
      }
    }

    return position.manhattanDistanceTo(Point(0, 0))
  }

  override fun solvePart2(input: String): Int {
    TODO("Not yet implemented")
  }

  private fun parseInstructions(input: String): List<Instruction> {
    val instructions = input.lines().filter { it.isNotEmpty() }.map {
      Instruction(
        count = it.drop(1).toInt(),
        action = Instruction.Action.byChar.getValue(it.first()),
      )
    }
    return instructions
  }

  private data class Instruction(val count: Int, val action: Action) {
    enum class Action {
      North,
      East,
      South,
      West,
      Left,
      Right,
      Forward,
      ;

      companion object {
        val byChar = Action.entries.associateBy { it.name.first() }
      }
    }
  }
}
