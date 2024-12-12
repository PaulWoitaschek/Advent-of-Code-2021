package aoc.year2020

import aoc.library.Puzzle

object Day6 : Puzzle<Int, Int>(6) {

  override fun solvePart1(input: String): Int = input.split("\n\n")
    .filter(String::isNotBlank).sumOf {
      it.replace("\n", "").toSet().count()
    }

  override fun solvePart2(input: String): Int = input.split("\n\n")
    .filter(String::isNotBlank).sumOf {
      val groups = it.lines().filter(String::isNotBlank)
      groups.first().toSet().count { answer ->
        groups.drop(1).all { groupAnswer ->
          answer in groupAnswer
        }
      }
    }
}
