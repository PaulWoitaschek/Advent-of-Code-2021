package aoc.year2017

import aoc.library.Puzzle
import kotlin.String

object Day10 : Puzzle<Int, String>(day = 10) {

  override fun solvePart1(input: String): Int = solvePart1(input = input, listSize = 256)

  fun solvePart1(
    input: String,
    listSize: Int,
  ): Int {
    val list = runRounds(
      inputLengths = input.split(",").map(String::toInt),
      times = 1,
      listSize = listSize,
    )
    return list[0] * list[1]
  }

  override fun solvePart2(input: String): String {
    val list = runRounds(
      inputLengths = input.map(Char::code) + listOf(17, 31, 73, 47, 23),
      times = 64,
      listSize = 256,
    )
    return list.chunked(16)
      .joinToString("") { values ->
        values.reduce(Int::xor)
          .toString(16)
          .padStart(2, '0')
      }
  }

  private fun runRounds(
    inputLengths: List<Int>,
    times: Int,
    listSize: Int,
  ): List<Int> {
    var position = 0
    var skipSize = 0
    val list = (0 until listSize).toMutableList()
    repeat(times) {
      inputLengths.forEach { inputLength ->
        val values = sequence {
          while (true) {
            yieldAll(list)
          }
        }.drop(position).take(inputLength).toList()
        val reversed = values.reversed()
        val start = (position).rem(list.size)
        reversed.forEachIndexed { index, value ->
          list[(start + index).rem(list.size)] = value
        }
        position = (position + inputLength + skipSize).rem(list.size)
        skipSize++
      }
    }
    return list
  }
}
