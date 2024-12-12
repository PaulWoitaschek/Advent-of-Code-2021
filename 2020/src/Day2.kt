package aoc.year2020

import aoc.library.Puzzle

object Day2 : Puzzle<Int, Int>(2) {

  override fun solvePart1(input: String): Int = parseInput(input).count(PasswordPolicy::isValidForPart1)

  override fun solvePart2(input: String): Int = parseInput(input).count(PasswordPolicy::isValidForPart2)

  private fun parseInput(input: String): List<PasswordPolicy> {
    val regex = "(\\d+)-(\\d+) (.): (.*)".toRegex()
    return input.lines().filter(String::isNotEmpty).map {
      val (min, max, char, password) = regex.find(it)!!.destructured
      PasswordPolicy(min = min.toInt(), max = max.toInt(), char = char.single(), password = password)
    }
  }

  private data class PasswordPolicy(val min: Int, val max: Int, val char: Char, val password: String) {
    fun isValidForPart1(): Boolean = password.count { it == char } in min..max
    fun isValidForPart2(): Boolean = listOf(min, max).count { password[it - 1] == char } == 1
  }
}
