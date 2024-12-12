package aoc.year2021

import aoc.library.Puzzle

object Day10 : Puzzle<Long, Long>(10) {

  override fun solvePart1(input: String): Long = input.lines()
    .map(NavigationSubSystem.Companion::parse)
    .sumOf { it.illegalScore().toLong() }

  override fun solvePart2(input: String): Long = input.lines()
    .map(NavigationSubSystem.Companion::parse)
    .filterNot { it.isCorrupted() }
    .map { it.closingSequencePoints() }
    .sorted()
    .let { it[it.size / 2] }
}

private data class NavigationSubSystem(private val chars: List<Char>) {

  fun illegalScore(): Int {
    val openings = mutableListOf<Char>()
    chars.forEach { char ->
      val surrounding = Surrounding.byChar(char)
      if (surrounding.isOpening(char)) {
        openings += char
      } else {
        val lastOpeningCharacter = openings.last()
        val expectedOpening = surrounding.opening
        if (expectedOpening == lastOpeningCharacter) {
          openings.removeLast()
        } else {
          return surrounding.illegalScore
        }
      }
    }
    return 0
  }

  fun closingSequencePoints(): Long {
    val openings = mutableListOf<Char>()
    chars.forEach { char ->
      val surrounding = Surrounding.byChar(char)
      if (surrounding.isOpening(char)) {
        openings += char
      } else {
        openings.removeLast()
      }
    }
    return openings.reversed().fold(0L) { acc, value ->
      (acc * 5) + Surrounding.byChar(value).closingScore
    }
  }

  fun isCorrupted(): Boolean = illegalScore() != 0

  companion object {
    fun parse(input: String): NavigationSubSystem = NavigationSubSystem(input.toList())
  }
}

private data class Surrounding(val opening: Char, val closing: Char, val illegalScore: Int, val closingScore: Int) {

  fun matches(char: Char): Boolean = char == opening || char == closing

  fun isOpening(char: Char): Boolean = when (char) {
    opening -> true
    closing -> false
    else -> error("Char $char does not match the surrounding")
  }

  companion object {

    fun byChar(char: Char): Surrounding = all.first { it.matches(char) }

    val all = listOf(
      Surrounding(opening = '(', closing = ')', illegalScore = 3, closingScore = 1),
      Surrounding(opening = '[', closing = ']', illegalScore = 57, closingScore = 2),
      Surrounding(opening = '{', closing = '}', illegalScore = 1197, closingScore = 3),
      Surrounding(opening = '<', closing = '>', illegalScore = 25137, closingScore = 4),
    )
  }
}
