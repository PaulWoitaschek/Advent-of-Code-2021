package aoc.year2020

import aoc.library.Puzzle

object Day8 : Puzzle<Int, Int>(8) {

  override fun solvePart1(input: String): Int = execute(parseInput(input)).first

  override fun solvePart2(input: String): Int = part2Permutations(parseInput(input))
    .map(::execute)
    .first { (_, exitResult) ->
      exitResult == ExitResult.ExitedNormally
    }
    .first

  private fun part2Permutations(instructions: List<Instruction>): Sequence<List<Instruction>> = sequence {
    yield(instructions)
    instructions.indices.forEach { index ->
      val instruction = instructions[index]
      when (instruction.type) {
        Instruction.Type.Acc -> {
          // ignore
        }
        Instruction.Type.NoOp -> {
          yield(
            instructions.toMutableList().apply {
              set(index, instruction.copy(type = Instruction.Type.Jump))
            },
          )
        }
        Instruction.Type.Jump -> {
          yield(
            instructions.toMutableList().apply {
              set(index, instruction.copy(type = Instruction.Type.NoOp))
            },
          )
        }
      }
    }
  }

  private enum class ExitResult {
    ExitedNormally,
    ExitDueToLoop,
  }

  private fun execute(instructions: List<Instruction>): Pair<Int, ExitResult> {
    var index = 0
    var accumulator = 0
    val visited = mutableSetOf<Int>()
    while (true) {
      val instruction = instructions.getOrNull(index) ?: return accumulator to ExitResult.ExitedNormally
      if (!visited.add(index)) {
        return accumulator to ExitResult.ExitDueToLoop
      }
      when (instruction.type) {
        Instruction.Type.NoOp -> {
          index += 1
        }
        Instruction.Type.Acc -> {
          index += 1
          accumulator += instruction.value
        }
        Instruction.Type.Jump -> {
          index += instruction.value
        }
      }
    }
  }

  private fun parseInput(input: String): List<Instruction> = input.lines()
    .filter(String::isNotEmpty)
    .map(Instruction::parse)

  private data class Instruction(val value: Int, val type: Type) {
    enum class Type(val value: String) {
      NoOp("nop"),
      Acc("acc"),
      Jump("jmp"),
    }

    companion object {
      fun parse(input: String): Instruction {
        val typeValue = input.take(3)
        val type = Type.entries.first { it.value == typeValue }
        val value = input.drop(4).toInt()
        return Instruction(value, type)
      }
    }
  }
}
