package aoc.year2017

import aoc.library.Puzzle
import kotlin.String

object Day5 : Puzzle<Int, Int>(day = 5) {

  override fun solvePart1(input: String): Int {
    val offsets = input.lines().map(String::toInt).toMutableList()
    var index = 0
    var step = 0
    while (true) {
      val offset = offsets.getOrNull(index) ?: return step
      step++
      offsets[index]++
      index += offset
    }
  }

  override fun solvePart2(input: String): Int {
    val offsets = input.lines().map(String::toInt).toMutableList()
    var index = 0
    var step = 0
    while (true) {
      val offset = offsets.getOrNull(index) ?: return step
      step++
      offsets[index] = offsets[index] + if (offset >= 3) -1 else 1
      index += offset
    }
  }
}
