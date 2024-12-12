package aoc.year2021

import aoc.library.Puzzle

object Day2 : Puzzle<Long, Long>(2) {

  override fun solvePart1(input: String): Long = process(input, accountAim = false)

  override fun solvePart2(input: String): Long = process(input, accountAim = true)
}

private fun process(
  input: String,
  accountAim: Boolean,
): Long {
  val position = input.lineSequence()
    .map { Instruction.parse(it) }
    .fold(Position.INITIAL) { position, instruction ->
      position.plusInstruction(instruction, accountAim)
    }
  return (position.depth * position.horizontal).toLong()
}

private enum class Connection {
  Forward,
  Up,
  Down,
}

private data class Position(val horizontal: Int, val depth: Int, val aim: Int) {

  fun plusInstruction(
    instruction: Instruction,
    accountAim: Boolean,
  ): Position {
    val steps = instruction.steps
    return if (accountAim) {
      when (instruction.direction) {
        Connection.Forward -> copy(
          horizontal = horizontal + steps,
          depth = depth + aim * steps,
        )
        Connection.Up -> copy(aim = aim - steps)
        Connection.Down -> copy(aim = aim + steps)
      }
    } else {
      when (instruction.direction) {
        Connection.Forward -> copy(horizontal = horizontal + steps)
        Connection.Up -> copy(depth = depth - steps)
        Connection.Down -> copy(depth = depth + steps)
      }
    }
  }

  companion object {
    val INITIAL = Position(0, 0, 0)
  }
}

private data class Instruction(val direction: Connection, val steps: Int) {
  companion object {
    fun parse(input: String): Instruction {
      val (directionString, stepString) = input.split(" ")
      val direction = when (directionString) {
        "forward" -> Connection.Forward
        "down" -> Connection.Down
        "up" -> Connection.Up
        else -> error("Invalid direction=$directionString")
      }
      val steps = stepString.toInt()
      return Instruction(steps = steps, direction = direction)
    }
  }
}
