package de.woitaschek.aoc.year2022

import de.woitaschek.aoc.Puzzle

object Day5 : Puzzle {
  override val day = 5
  override fun solvePart1(input: String) = input.solve(moveOneByOne = true)
  override fun solvePart2(input: String) = input.solve(moveOneByOne = false)
}

private fun String.solve(moveOneByOne: Boolean): String {
  val (crates, moveInstructions) = parseInput()
  return moveInstructions.fold(crates) { currentCrates, instruction ->
    currentCrates.move(instruction, moveOneByOne = moveOneByOne)
  }.onTopOfStack()
}

@JvmInline
private value class Crates(val stacks: List<List<Char>>) {
  fun move(instruction: MoveInstruction, moveOneByOne: Boolean): Crates {
    return Crates(
      stacks = stacks.toMutableList().apply {
        if (moveOneByOne) {
          val fromStack = this[instruction.from].toMutableList()
          val toStack = this[instruction.to].toMutableList()
          repeat(instruction.count) {
            toStack.add(fromStack.removeLast())
          }
          this[instruction.from] = fromStack
          this[instruction.to] = toStack
        } else {
          val fromStack = this[instruction.from]
          val toStack = this[instruction.to]
          val splitIndex = fromStack.count() - instruction.count
          val keep = fromStack.take(splitIndex)
          val move = fromStack.drop(splitIndex)
          this[instruction.from] = keep
          this[instruction.to] = toStack + move
        }
      },
    )
  }

  fun onTopOfStack(): String {
    return stacks.mapNotNull { it.lastOrNull() }.joinToString(separator = "")
  }

  companion object {
    fun parse(input: String): Crates {
      val cratesMap = mutableMapOf<Int, MutableList<Char>>()
      input.lines().reversed().drop(1).forEach {
        it.chunked(4)
          .forEachIndexed { index, value ->
            val type = value[1]
            if (type != ' ') {
              val list = cratesMap.getOrPut(index) { mutableListOf() }
              list.add(type)
            }
          }
      }
      return Crates(cratesMap.toSortedMap().map { it.value })
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
