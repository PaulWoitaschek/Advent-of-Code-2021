package aoc.year2017

import aoc.library.Puzzle

object Day17 : Puzzle<Int, Int>(day = 17) {

  override fun solvePart1(input: String): Int {
    val step = input.toInt()
    val buffer = mutableListOf(0)
    var position = 0
    repeat(2017) {
      position = (position + step).rem(buffer.size) + 1
      buffer.add(position, it + 1)
    }
    return buffer[position + 1]
  }

  override fun solvePart2(input: String): Int {
    val step = input.toInt()
    var position = 0
    var bufferSize = 1
    var value = 0
    repeat(50_000_000) {
      position = (position + step) % bufferSize + 1
      if (position == 1) {
        value = it + 1
      }
      bufferSize++
    }
    return value
  }
}
