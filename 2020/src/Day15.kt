package aoc.year2020

import aoc.library.Puzzle

object Day15 : Puzzle<Int, Int>(15) {

  override fun solvePart1(input: String): Int = solve(input, 2020)

  override fun solvePart2(input: String): Int = solve(input, 30000000)

  private fun speak(input: String): Sequence<Int> = sequence {
    val initial = input.split(",").map(String::toInt)

    var mostRecentlySpoken = initial.last()

    data class Speak(var last: Int, var before: Int? = null) {
      fun firstTimeSpoken() = before == null
    }

    val spoken = initial.withIndex().associate {
      it.value to Speak(last = it.index + 1)
    }.toMutableMap()

    yieldAll(initial)

    var turn = initial.size
    while (true) {
      turn++
      val speak = spoken.getValue(mostRecentlySpoken)
      fun speak(number: Int) {
        val spokenNumber = spoken[number]
        if (spokenNumber == null) {
          spoken[number] = Speak(last = turn)
        } else {
          spokenNumber.before = spokenNumber.last
          spokenNumber.last = turn
        }
        mostRecentlySpoken = number
      }

      if (speak.firstTimeSpoken()) {
        speak(0)
      } else {
        speak(turn - 1 - speak.before!!)
      }
      yield(mostRecentlySpoken)
    }
  }

  private fun solve(
    input: String,
    turns: Int,
  ): Int = speak(input).drop(turns - 1).first()
}
