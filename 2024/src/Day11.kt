package aoc.year2024

import aoc.library.Puzzle

object Day11 : Puzzle<Long, Long>(day = 11) {

  override fun solvePart1(input: String): Long = solve(input, blinks = 25)

  override fun solvePart2(input: String): Long = solve(input, blinks = 75)

  private fun solve(
    input: String,
    blinks: Int,
  ): Long {
    var numbers = parse(input)
    repeat(blinks) {
      numbers = numbers.blink()
    }
    return numbers.values.sum()
  }

  private fun parse(input: String): Map<Long, Long> = input.split(" ")
    .groupingBy(String::toLong)
    .eachCount()
    .mapValues { it.value.toLong() }

  private fun Map<Long, Long>.blink(): Map<Long, Long> {
    val afterBlink = mutableMapOf<Long, Long>()
    forEach { (number, count) ->
      number.blink().forEach { newNumber ->
        val current = afterBlink[newNumber] ?: 0
        afterBlink[newNumber] = current + count
      }
    }
    return afterBlink
  }

  private fun Long.blink(): List<Long> = when {
    this == 0L -> listOf(1)
    toString().length % 2 == 0 -> {
      val asString = this.toString()
      val center = asString.length / 2
      listOf(
        asString.take(center).toLong(),
        asString.drop(center).toLong(),
      )
    }
    else -> listOf(this * 2024)
  }
}
