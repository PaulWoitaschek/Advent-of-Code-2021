package aoc.year2017

import aoc.library.Puzzle
import java.util.Collections

object Day16 : Puzzle<String, String>(day = 16) {

  override fun solvePart1(input: String): String = solve(input, programCount = 16, danceRounds = 1)

  override fun solvePart2(input: String): String = solve(input, programCount = 16, danceRounds = 1000000000)

  fun solve(
    input: String,
    programCount: Int,
    danceRounds: Int,
  ): String {
    var programs = List(programCount) { Char('a'.code + it) }.joinToString("")
    val instructions = input.split(",").map(Instruction::parse)
    val dances = mutableMapOf<String, String>()
    var round = 0
    var loopSkipped = false
    while (++round <= danceRounds) {
      if (!loopSkipped && dances.containsKey(programs)) {
        val remaining = danceRounds - round
        round = danceRounds - remaining.rem(round)
        programs = programs.dance(instructions)
        loopSkipped = true
      }
      programs = dances.getOrPut(programs) { programs.dance(instructions) }
    }
    return programs
  }

  private sealed interface Instruction {
    data class Spin(val size: Int) : Instruction
    data class Exchange(val programAIndex: Int, val programBIndex: Int) : Instruction
    data class Partner(val programA: Char, val programB: Char) : Instruction
    companion object {
      fun parse(instruction: String): Instruction = when (instruction.first()) {
        's' -> {
          val position = instruction.drop(1).toInt()
          Spin(position)
        }
        'x' -> {
          val (p1Index, p2Index) = instruction.drop(1).split("/").map(String::toInt)
          Exchange(p1Index, p2Index)
        }
        'p' -> {
          val (p1, p2) = instruction.drop(1).split("/").map(String::single)
          Partner(p1, p2)
        }
        else -> error("Invalid instruction $instruction")
      }
    }
  }

  private fun String.dance(instructions: List<Instruction>): String {
    val programs = toMutableList()
    fun swap(
      positionA: Int,
      positionB: Int,
    ) {
      val (p1Index, p2Index) = listOf(positionA, positionB).sorted()
      val p1 = programs.removeAt(p1Index)
      val p2 = programs.removeAt(p2Index - 1)
      programs.add(p1Index, p2)
      programs.add(p2Index, p1)
    }
    instructions.forEach { instruction ->
      when (instruction) {
        is Instruction.Exchange -> {
          swap(instruction.programAIndex, instruction.programBIndex)
        }
        is Instruction.Partner -> {
          swap(programs.indexOf(instruction.programA), programs.indexOf(instruction.programB))
        }
        is Instruction.Spin -> {
          Collections.rotate(programs, instruction.size)
        }
      }
    }
    return programs.joinToString("")
  }
}
