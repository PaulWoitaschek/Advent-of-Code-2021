package aoc.year2022

import aoc.library.Point
import aoc.library.Puzzle
import aoc.year2022.Day22.Facing.Right

private typealias Board = List<List<Day22.Tile>>

object Day22 : Puzzle<Int, Int>(22) {

  override fun solvePart1(input: String): Int {
    val (mapString, instructions) = input.split("\n\n")

    val mapLines = mapString.lines()
    val width = mapLines.maxOf { it.count() }

    val board = mapLines.map { line ->
      List(width) {
        when (line.getOrNull(it)) {
          null, ' ' -> Tile.Void
          '.' -> Tile.Floor
          '#' -> Tile.Wall
          else -> error("Unknown tile=$it")
        }
      }
    }

    val height = board.size

    val result = mutableListOf<Instruction>()
    var buffer = ""
    val trimmedInstructions = instructions.trim().removeSurrounding("\n")
    trimmedInstructions.forEachIndexed { index, c ->
      val isLast = index == trimmedInstructions.lastIndex
      when {
        c.isDigit() -> {
          buffer += c
          if (isLast) {
            result += Instruction.Move(buffer.toInt())
          }
        }
        c == 'R' -> {
          if (buffer.isNotEmpty()) {
            result += Instruction.Move(buffer.toInt())
            buffer = ""
          }
          result += Instruction.TurnRight
        }
        c == 'L' -> {
          if (buffer.isNotEmpty()) {
            result += Instruction.Move(buffer.toInt())
            buffer = ""
          }
          result += Instruction.TurnLeft
        }
        else -> {
          error("Unhandled char=$c")
        }
      }
    }

    var facing = Right

    var position = board.startingPosition()

    result.forEach { instruction ->
      when (instruction) {
        is Instruction.Move -> {
          repeat(instruction.steps) {
            val previousPosition = position
            while (true) {
              position = Point(
                x = (position.x + facing.x).let {
                  when {
                    it < 0 -> width - 1
                    it > width - 1 -> 0
                    else -> it
                  }
                },
                y = (position.y + facing.y).let {
                  when {
                    it < 0 -> height - 1
                    it > height - 1 -> 0
                    else -> it
                  }
                },
              )
              when (board[position.y][position.x]) {
                Tile.Wall -> {
                  position = previousPosition
                  break
                }
                Tile.Floor -> break
                Tile.Void -> continue
              }
            }
          }
        }
        Instruction.TurnLeft -> facing = facing.turnLeft()
        Instruction.TurnRight -> facing = facing.turnRight()
      }
    }
    return 1000 * (position.y + 1) + 4 * (position.x + 1) + facing.score
  }

  private fun Board.startingPosition(): Point {
    forEachIndexed { y, tiles ->
      tiles.forEachIndexed { x, tile ->
        if (tile == Tile.Floor) {
          return Point(x, y)
        }
      }
    }
    error("No starting position found.")
  }

  enum class Facing(val x: Int, val y: Int, val score: Int) {
    Left(-1, 0, 2),
    Up(0, -1, 3),
    Right(1, 0, 0),
    Down(0, 1, 1),
    ;

    fun turnLeft(): Facing = entries.getOrNull(ordinal - 1) ?: entries.last()
    fun turnRight(): Facing = entries.getOrNull(ordinal + 1) ?: entries.first()
  }

  sealed interface Instruction {
    @JvmInline
    value class Move(val steps: Int) : Instruction
    data object TurnLeft : Instruction

    data object TurnRight : Instruction
  }

  enum class Tile {
    Wall,
    Floor,
    Void,
  }

  override fun solvePart2(input: String) = TODO("Not yet implemented")
}
