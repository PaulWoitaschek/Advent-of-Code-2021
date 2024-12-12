package aoc.year2023

import aoc.library.Puzzle

object Day6 : Puzzle<Int, Int>(day = 6) {

  override fun solvePart1(input: String): Int = Round.parseAsMultipleRounds(input)
    .map(Round::waysToSolve)
    .reduce(Int::times)

  override fun solvePart2(input: String): Int = Round.parseAsSingleRound(input).waysToSolve()

  private data class Round(val time: Long, val recordDistance: Long) {

    private fun accelerationStepsWin(steps: Long): Boolean = (time - steps) * steps > recordDistance

    fun waysToSolve(): Int {
      val start = (0..time).first(::accelerationStepsWin)
      val end = (time downTo 0).first(::accelerationStepsWin)
      return (end - start + 1).toInt()
    }

    companion object {
      fun parseAsMultipleRounds(input: String): List<Round> {
        val values = input.lines().map { line ->
          line.split(' ').mapNotNull(String::toLongOrNull)
        }
        return values[0].zip(values[1]).map { Round(it.first, it.second) }
      }

      fun parseAsSingleRound(input: String): Round {
        val values = input.lines().map { line ->
          line.filter(Char::isDigit).toLong()
        }
        return Round(time = values[0], recordDistance = values[1])
      }
    }
  }
}
