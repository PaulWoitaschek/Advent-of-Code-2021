package aoc.year2023

import aoc.library.Puzzle

object Day1 : Puzzle<Int, Int>(1) {

  override fun solvePart1(input: String): Int = input.lines()
    .map {
      val firstDigit = it.first(Char::isDigit)
      val lastDigit = it.reversed().first(Char::isDigit)
      "$firstDigit$lastDigit".toInt()
    }
    .fold(0, Int::plus)

  override fun solvePart2(input: String): Int {
    val numbers = buildMap {
      listOf(
        1 to "one",
        2 to "two",
        3 to "three",
        4 to "four",
        5 to "five",
        6 to "six",
        7 to "seven",
        8 to "eight",
        9 to "nine",
      ).forEach { (number, name) ->
        put(number.toString(), number)
        put(name, number)
      }
    }

    return input.lines()
      .map { line ->
        val min = numbers.getValue(line.findAnyOf(numbers.keys)!!.second)
        val max = numbers.getValue(line.findLastAnyOf(numbers.keys)!!.second)
        "$min$max".toInt()
      }
      .fold(0, Int::plus)
  }
}
