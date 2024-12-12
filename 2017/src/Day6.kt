package aoc.year2017

import aoc.library.Puzzle
import kotlin.String

object Day6 : Puzzle<Int, Int>(day = 6) {

  override fun solvePart1(input: String): Int = solve(input).atCycle

  override fun solvePart2(input: String): Int = solve(input).cycleLength

  private fun redistribute(banks: List<Int>): List<Int> {
    val redistributed = banks.toMutableList()
    val max = banks.max()
    val initialIndex = banks.indexOf(max)
    redistributed[initialIndex] = 0
    repeat(max) {
      redistributed[(initialIndex + it + 1) % (banks.size)]++
    }
    return redistributed
  }

  private fun solve(input: String): Seen {
    var banks = input.split("\t").map(String::toInt)
    var cycle = 0
    val seen = mutableSetOf<List<Int>>()
    while (seen.add(banks)) {
      cycle++
      banks = redistribute(banks)
    }
    return Seen(
      atCycle = cycle,
      cycleLength = cycle - seen.indexOf(banks),
    )
  }

  private data class Seen(val atCycle: Int, val cycleLength: Int)
}
