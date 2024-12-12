package aoc.year2022

import aoc.library.Puzzle

object Day5 : Puzzle<String, String>(5) {
  override fun solvePart1(input: String) = input.solve(moveOneByOne = true)
  override fun solvePart2(input: String) = input.solve(moveOneByOne = false)
}

private fun String.solve(moveOneByOne: Boolean): String {
  val (crates, moveInstructions) = parseInput()
  moveInstructions.forEach {
    crates.move(it, moveOneByOne = moveOneByOne)
  }
  return crates.onTopOfStack()
}

@JvmInline
private value class Crates(val stacks: List<MutableList<Char>>) {
  fun move(
    instruction: MoveInstruction,
    moveOneByOne: Boolean,
  ) {
    if (moveOneByOne) {
      val fromStack = stacks[instruction.from]
      val toStack = stacks[instruction.to]
      repeat(instruction.count) {
        toStack.add(fromStack.removeLast())
      }
    } else {
      val fromStack = stacks[instruction.from]
      val toStack = stacks[instruction.to]
      val splitIndex = fromStack.count() - instruction.count
      repeat(instruction.count) {
        toStack.add(fromStack.removeAt(splitIndex))
      }
    }
  }

  fun onTopOfStack(): String = String(stacks.mapNotNull { it.lastOrNull() }.toCharArray())

  companion object {
    fun parse(input: String): Crates {
      val lines = input.lines().reversed().drop(1)
      return Crates(
        List(9) { index ->
          lines.mapNotNull {
            it.getOrNull(1 + index * 4)?.takeIf { char ->
              char in 'A'..'Z'
            }
          }.toMutableList()
        },
      )
    }
  }
}

private data class MoveInstruction(val count: Int, val from: Int, val to: Int) {
  companion object {
    fun parse(input: String): MoveInstruction {
      val (count, from, to) = input.split("move ", " from ", " to ").drop(1).map(String::toInt)
      return MoveInstruction(count = count, from = from - 1, to = to - 1)
    }
  }
}

private fun String.parseInput(): Pair<Crates, List<MoveInstruction>> {
  val (cratesString, instructions) = split("\n\n")
  val crates = Crates.parse(cratesString)
  val moveInstructions = instructions.lines().filter { it.isNotEmpty() }
    .map(MoveInstruction::parse)
  return crates to moveInstructions
}
