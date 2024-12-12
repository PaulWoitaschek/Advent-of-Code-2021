package aoc.year2023

import aoc.library.Puzzle

object Day13 : Puzzle<Int, Int>(day = 13) {

  override fun solvePart1(input: String): Int = input.split("\n\n").sumOf { pattern ->
    reflectionLines(pattern.lines()).single().value()
  }

  override fun solvePart2(input: String): Int = input.split("\n\n").sumOf { pattern ->
    val old = reflectionLines(pattern.lines()).single()
    val reflectionLine = smudgeReflections(pattern).first { it != old }
    reflectionLine.value()
  }

  private fun reflectionLines(input: List<String>): List<ReflectionLine> {
    fun line(lines: List<String>): List<Int> = lines
      .zipWithNext { a, b -> a == b }
      .mapIndexedNotNull { index, equal ->
        if (equal) index + 1 else null
      }
      .filter {
        val leftPart = lines.take(it).reversed()
        val rightPart = lines.drop(it)
        leftPart
          .zip(rightPart) { left, right ->
            left == right
          }
          .all { equal -> equal }
      }

    val top = line(input).map {
      ReflectionLine(horizontal = true, it)
    }
    val rotated = List(input.first().length) { rowIndex ->
      input.joinToString("") { it[rowIndex].toString() }
    }
    val left = line(rotated).map {
      ReflectionLine(horizontal = false, it)
    }
    return top + left
  }

  private fun smudgeReflections(input: String): Sequence<ReflectionLine> = sequence {
    suspend fun SequenceScope<ReflectionLine>.applySmudgeAt(index: Int) {
      val smudge = when (input[index]) {
        '#' -> "."
        '.' -> "#"
        else -> null
      }
      if (smudge != null) {
        val withSmudge = input.replaceRange(index, index + 1, smudge)
        yieldAll(reflectionLines(withSmudge.lines()))
      }
      if (index + 1 <= input.lastIndex) {
        applySmudgeAt(index + 1)
      }
    }
    applySmudgeAt(0)
  }

  private data class ReflectionLine(val horizontal: Boolean, val before: Int) {

    fun value(): Int = if (horizontal) before * 100 else before
  }
}
