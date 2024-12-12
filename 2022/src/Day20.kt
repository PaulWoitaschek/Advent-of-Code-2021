package aoc.year2022

import aoc.library.Puzzle
import java.util.Collections

object Day20 : Puzzle<Long, Long>(20) {

  override fun solvePart1(input: String): Long = solve(input = input, decryptionKey = 1, mix = 1)
  override fun solvePart2(input: String) = solve(input = input, decryptionKey = 811589153, mix = 10)

  private fun solve(
    input: String,
    decryptionKey: Long,
    mix: Int,
  ): Long {
    val numbers = input.lines().filter(String::isNotEmpty).map(String::toInt)
    val mixedNumbers = mixNumbers(numbers, decryptionKey, mix)
    return generateSequence { mixedNumbers }.flatten()
      .dropWhile { it != 0L }
      .windowed(size = 1, step = 1000) { it.single() }
      .drop(1)
      .take(3)
      .sum()
  }

  private fun mixNumbers(
    numbers: List<Int>,
    decryptionKey: Long,
    mix: Int,
  ): List<Long> {
    val mixedNumbers = numbers.mapIndexed { index, value ->
      NumberWithIndex(number = value * decryptionKey, index = index)
    }.toMutableList()
    repeat(mix) {
      numbers.indices.forEach { index ->
        val toMove = mixedNumbers.first { it.index == index }
        if (toMove.number != 0L) {
          val toMoveIndex = mixedNumbers.indexOf(toMove)
          mixedNumbers.removeAt(toMoveIndex)
          Collections.rotate(mixedNumbers, (-toMove.number % mixedNumbers.size).toInt())
          mixedNumbers.add(toMoveIndex, toMove)
        }
      }
    }
    return mixedNumbers.map { it.number }
  }

  private data class NumberWithIndex(val number: Long, val index: Int)
}
