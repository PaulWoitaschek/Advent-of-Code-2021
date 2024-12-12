package aoc.year2019

import aoc.library.Puzzle

private const val WIDTH = 25
private const val HEIGHT = 6
private const val SIZE = WIDTH * HEIGHT

object Day8 : Puzzle<Int, String>(8) {

  override fun solvePart1(input: String): Int = layers(input)
    .map { it.groupBy { value -> value } }
    .minBy { it.getValue(0).size }
    .let {
      it.getValue(1).size * it.getValue(2).size
    }

  override fun solvePart2(input: String): String {
    val layers = layers(input)
    return List(SIZE) { index ->
      layers.map { it[index] }.first { it != 2 }
    }
      .chunked(WIDTH)
      .joinToString(separator = "\n") {
        it.joinToString(separator = "") { color ->
          when (color) {
            1 -> "⬜"
            0 -> "⬛"
            else -> error("Invalid color=$color")
          }
        }
      }
  }

  private fun layers(input: String): List<List<Int>> = input
    .map(Char::digitToInt)
    .chunked(SIZE)
}
