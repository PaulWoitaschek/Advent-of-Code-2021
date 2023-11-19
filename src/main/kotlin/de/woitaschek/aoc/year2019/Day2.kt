package de.woitaschek.aoc.year2019

import de.woitaschek.aoc.utils.Puzzle
import de.woitaschek.aoc.utils.toCommaSeparatedLongList
import kotlinx.coroutines.runBlocking

object Day2 : Puzzle<Long, Long>(2019, 2) {

  override fun solvePart1(input: String): Long {
    return runInstructions(instructions = input.toCommaSeparatedLongList(), verb = 2, noun = 12)
  }

  override fun solvePart2(input: String): Long {
    val originalInput = input.toCommaSeparatedLongList()
    var max = 0
    while (true) {
      max++
      (0..max).forEach { verb ->
        (0..max).forEach { noun ->
          val out = runInstructions(
            instructions = originalInput,
            verb = verb.toLong(),
            noun = noun.toLong(),
          )
          if (out == 19690720L) {
            return 100L * noun + verb
          }
        }
      }
    }
  }

  private fun runInstructions(
    instructions: List<Long>,
    verb: Long,
    noun: Long,
  ): Long {
    val computer = IntCodeComputer(
      instructions
        .toMutableList()
        .apply {
          set(1, noun)
          set(2, verb)
        },
      emptyList(),
    )
    runBlocking {
      computer.run()
    }
    return computer.firstInstruction
  }
}
