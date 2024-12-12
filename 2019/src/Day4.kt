package aoc.year2019

import aoc.library.Puzzle

object Day4 : Puzzle<Int, Int>(4) {

  override fun solvePart1(input: String): Int = solve(input, ::isValidPasswordPart1)

  override fun solvePart2(input: String): Int = solve(input, ::isValidPasswordPart2)

  private fun solve(
    input: String,
    isValidPassword: (Int) -> Boolean,
  ): Int {
    val (from, to) = input
      .split('-')
      .map(String::toInt)
    return (from..to).count(isValidPassword)
  }

  fun isValidPasswordPart1(password: Int): Boolean {
    val digits = password.toString().map(Char::digitToInt)
    if (digits.sorted() != digits) {
      return false
    }
    return digits.windowed(2).any { (first, second) ->
      first == second
    }
  }

  fun isValidPasswordPart2(password: Int): Boolean {
    val digits = password.toString().map(Char::digitToInt)
    if (digits.sorted() != digits) {
      return false
    }
    var counter = 0
    var lastDigit = digits.first()
    digits.forEach { digit ->
      if (digit == lastDigit) {
        counter++
      } else {
        if (counter == 2) return true
        counter = 1
      }
      lastDigit = digit
    }
    return counter == 2
  }
}
