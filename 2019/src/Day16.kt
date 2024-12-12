package aoc.year2019

import aoc.library.Puzzle
import kotlin.math.absoluteValue

object Day16 : Puzzle<String, String>(16) {

  override fun solvePart1(input: String): String = process(input, 100).take(8)

  override fun solvePart2(input: String): String {
    TODO()
  }

  fun process(
    input: String,
    phases: Int,
  ): String = process(
    digits = input.toList().map { it.digitToInt() },
    phases = phases,
  ).joinToString("")

  private fun process(
    digits: List<Int>,
    phases: Int,
  ): List<Int> {
    val patterns = digits.indices.map { index ->
      pattern(index = index, size = digits.size)
    }
    var current = digits
    repeat(phases) {
      current = current.indices
        .map { index ->
          current.zip(patterns[index], Int::times).sum().rem(10).absoluteValue
        }
    }
    return current
  }

  private fun pattern(
    index: Int,
    size: Int,
  ): List<Int> {
    val basePattern = listOf(0, 1, 0, -1)
    return sequence {
      while (true) {
        basePattern.forEach { value ->
          repeat(index + 1) {
            yield(value)
          }
        }
      }
    }.drop(1).take(size).toList()
  }
}
