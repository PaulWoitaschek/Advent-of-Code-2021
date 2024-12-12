package aoc.year2023

import aoc.library.Direction
import aoc.library.Point
import aoc.library.Puzzle
import aoc.library.move
import aoc.library.shoelace

object Day18 : Puzzle<Long, Long>(day = 18) {

  override fun solvePart1(input: String): Long = solve(input, Instruction::parseForPart1)

  override fun solvePart2(input: String): Long = solve(input, Instruction::parseForPart2)

  private fun solve(
    input: String,
    parseInstruction: (String) -> Instruction,
  ): Long {
    val instructions = input.lines().map(parseInstruction)
    val points = instructions.runningFold(Point.Zero) { point, instruction ->
      point.move(instruction.direction, instruction.steps)
    }
    val borderSize = instructions.sumOf { it.steps } / 2
    return shoelace(points) + borderSize + 1
  }

  private data class Instruction(val direction: Direction, val steps: Int) {
    companion object {

      private val part1Regex = """(\w) (\d+)""".toRegex()

      fun parseForPart1(input: String): Instruction {
        val (direction, steps) = part1Regex.find(input)!!.destructured
        return Instruction(
          direction = Direction.parse(direction.single()),
          steps = steps.toInt(),
        )
      }

      private val part2Regex = """.*?#(.{5})(\d)""".toRegex()

      fun parseForPart2(input: String): Instruction {
        val (steps, direction) = part2Regex.find(input)!!.destructured
        return Instruction(
          direction = when (direction.toInt()) {
            0 -> Direction.Right
            1 -> Direction.Down
            2 -> Direction.Left
            3 -> Direction.Up
            else -> error("Invalid direction")
          },
          steps = steps.toInt(radix = 16),
        )
      }
    }
  }
}
