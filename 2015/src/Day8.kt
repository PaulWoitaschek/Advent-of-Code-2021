package aoc.year2015

import aoc.library.Puzzle

object Day8 : Puzzle<Int, Int>(8) {

  override fun solvePart1(input: String): Int = input.lines()
    .sumOf { it.length - memoryCount(it) }

  override fun solvePart2(input: String): Int = input.lines()
    .sumOf { encodedSize(it) - it.length }

  fun memoryCount(string: String): Int = string.replace("""(\\\\|\\"|\\x..)""".toRegex(), "d").length - 2

  fun encodedSize(string: String): Int = string.length + string.count { it == '"' } + string.count { it == '\\' } + 2
}
