package aoc.year2018

import aoc.library.Puzzle

object Day5 : Puzzle<Int, Int>(day = 5) {

  override fun solvePart1(input: String): Int = lengthAfterReduction(input)

  override fun solvePart2(input: String): Int {
    val uniqueChars = input.filter(Char::isLowerCase).toSet()
    return uniqueChars.minOf { char ->
      lengthAfterReduction(input.filterNot { it.equals(char, ignoreCase = true) })
    }
  }

  private fun lengthAfterReduction(original: String): Int {
    var current = original
    while (true) {
      val polymerBuilder = StringBuilder()
      current.forEach { char ->
        val last = polymerBuilder.lastOrNull()
        if (last != null && last != char && last.equals(char, ignoreCase = true)) {
          polymerBuilder.deleteAt(polymerBuilder.lastIndex)
        } else {
          polymerBuilder.append(char)
        }
      }
      val polymer = polymerBuilder.toString()
      if (polymer == current) {
        return polymer.length
      } else {
        current = polymer
      }
    }
  }
}
