package aoc.year2017

import aoc.library.Puzzle

object Day7 : Puzzle<String, Int>(day = 7) {

  override fun solvePart1(input: String): String {
    val programs = input.lines().map(Program::parse)
    return findBottomProgram(programs).name
  }

  override fun solvePart2(input: String): Int {
    TODO()
  }

  private fun findBottomProgram(programs: List<Program>): Program = programs.first { candidate ->
    programs.none { candidate.name in it.above }
  }

  private data class Program(val name: String, val weight: Int, val above: List<String>) {
    companion object {
      private val regex = """(\w+) \((\d+)\)(?: -> )?([\w, ]+)?$""".toRegex()
      fun parse(input: String): Program {
        val (name, weight, above) = regex.find(input)!!.destructured
        return Program(
          name = name,
          weight = weight.toInt(),
          above = above.split(", "),
        )
      }
    }
  }
}
