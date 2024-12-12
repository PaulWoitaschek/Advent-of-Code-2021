package aoc.year2021

import aoc.library.Puzzle

object Day8 : Puzzle<Long, Long>(8) {

  override fun solvePart1(input: String): Long = parseInput(input).sumOf { line ->
    line.output.count {
      it.size == 2 || it.size == 4 || it.size == 3 || it.size == 7
    }.toLong()
  }

  override fun solvePart2(input: String): Long = parseInput(input).sumOf { it.outputValue().toLong() }

  private fun parseInput(input: String): List<Display> = input.lines().map(Display.Companion::parse)
}

data class Display(val input: List<Set<Char>>, val output: List<Set<Char>>) {

  fun outputValue(): Int {
    val one = input.single { it.count() == 2 }
    val four = input.single { it.count() == 4 }
    val seven = input.single { it.count() == 3 }
    val eight = input.single { it.count() == 7 }

    val fiveDigitChars = input.filter { it.size == 5 }
    val sixDigitChars = input.filter { it.size == 6 }

    val three = fiveDigitChars.single { it.containsAll(one) }
    val nine = sixDigitChars.single { it.containsAll(four) }
    val five = fiveDigitChars.single { it != three && nine.containsAll(it) }
    val two = fiveDigitChars.single { it != five && it != three }
    val zero = sixDigitChars.single { it != nine && it.containsAll(one) }
    val six = sixDigitChars.single { it != zero && it != nine }

    return output
      .map {
        when (it) {
          zero -> 0
          one -> 1
          two -> 2
          three -> 3
          four -> 4
          five -> 5
          six -> 6
          seven -> 7
          eight -> 8
          nine -> 9
          else -> error("Could not map $it")
        }
      }
      .joinToString(separator = "") { it.toString() }
      .toInt()
  }

  companion object {
    fun parse(input: String): Display {
      val (left, right) = input.split(" | ")
      return Display(
        input = left.split(" ").map { it.toSet() },
        output = right.split(" ").map { it.toSet() },
      )
    }
  }
}
