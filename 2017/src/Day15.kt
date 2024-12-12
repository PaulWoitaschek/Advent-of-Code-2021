package aoc.year2017

import aoc.library.Puzzle

object Day15 : Puzzle<Int, Int>(day = 15) {

  override fun solvePart1(input: String): Int {
    var (a, b) = input.lines().map { it.substringAfter("with ").toLong() }
    var count = 0
    repeat(40_000_000) {
      a = a * 16807 % Int.MAX_VALUE
      b = b * 48271 % Int.MAX_VALUE
      if (a.toShort() == b.toShort()) count++
    }
    return count
  }

  override fun solvePart2(input: String): Int {
    val (a, b) = input.lines().map { it.substringAfter("with ").toLong() }

    val aSequence = generateSequence(a) {
      it * 16807 % Int.MAX_VALUE
    }.filter { it.rem(4) == 0L }

    val bSequence = generateSequence(b) {
      it * 48271 % Int.MAX_VALUE
    }.filter { it.rem(8) == 0L }

    return aSequence
      .zip(bSequence) { left, right ->
        left.toShort() == right.toShort()
      }
      .take(5_000_000)
      .count { it }
  }
}
