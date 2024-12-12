package aoc.year2022

import aoc.library.Puzzle
import aoc.year2022.Day25.SnafuNumber.Companion.toSnafuNumber
import kotlin.math.pow

object Day25 : Puzzle<String, Nothing>(25) {

  override fun solvePart1(input: String): String = input.lines().filter(String::isNotEmpty)
    .map(::SnafuNumber)
    .sumOf(SnafuNumber::toDecimal)
    .toSnafuNumber()
    .value

  override fun solvePart2(input: String) = error("No part 2!")

  @JvmInline
  value class SnafuNumber(val value: String) {
    fun toDecimal(): Long = value.reversed().mapIndexed { index, char ->
      (5.0.pow(index) * charMapping.getValue(char)).toLong()
    }.sum()

    override fun toString(): String = value

    companion object {

      private val charMapping = mapOf(
        '2' to 2L,
        '1' to 1L,
        '0' to 0L,
        '-' to -1L,
        '=' to -2L,
      )
      private val intMapping: Map<Long, Char> = charMapping.toList().associate { it.second to it.first }

      fun Long.toSnafuNumber(): SnafuNumber {
        if (this == 0L) return SnafuNumber("0")
        var remaining = this
        return SnafuNumber(
          buildString {
            while (remaining > 0) {
              append(intMapping.getValue((remaining + 2) % 5 - 2))
              remaining = (remaining + 2) / 5
            }
          }.reversed(),
        )
      }
    }
  }
}
