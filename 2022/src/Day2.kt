package aoc.year2022

import aoc.library.Puzzle

object Day2 : Puzzle<Int, Int>(2) {

  override fun solvePart1(input: String): Int = input.lines()
    .filter { it.isNotBlank() }
    .sumOf { round ->
      val opponent = RPS.byChar.getValue(round[0])
      val answer = RPS.byChar.getValue(round[2])
      val match = answer.match(opponent)
      match.score + answer.score
    }

  override fun solvePart2(input: String): Int = input.lines()
    .filter { it.isNotBlank() }
    .sumOf { round ->
      val opponent = RPS.byChar.getValue(round[0])
      val desiredMatchResult = MatchResult.byChar.getValue(round[2])
      val response = RPS.entries.first { it.match(opponent) == desiredMatchResult }
      desiredMatchResult.score + response.score
    }
}

enum class RPS(val score: Int) {
  Rock(1),
  Paper(2),
  Scissor(3),
  ;

  fun match(opponent: RPS): MatchResult {
    if (this == opponent) {
      return MatchResult.Draw
    }
    val won = when (this) {
      Rock -> opponent == Scissor
      Paper -> opponent == Rock
      Scissor -> opponent == Paper
    }
    return if (won) {
      MatchResult.Won
    } else {
      MatchResult.Loss
    }
  }

  companion object {
    val byChar = mapOf(
      'A' to Rock,
      'X' to Rock,
      'B' to Paper,
      'Y' to Paper,
      'C' to Scissor,
      'Z' to Scissor,
    )
  }
}

enum class MatchResult(val score: Int) {
  Won(6),
  Loss(0),
  Draw(3),
  ;

  companion object {
    val byChar = mapOf('X' to Loss, 'Y' to Draw, 'Z' to Won)
  }
}
