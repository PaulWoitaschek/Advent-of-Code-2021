package aoc.year2018

import aoc.library.Puzzle

object Day4 : Puzzle<Int, Int>(day = 4) {

  override fun solvePart1(input: String): Int {
    val sleepyGuard = guards(input).maxBy { it.sleepTimes.size }
    return sleepyGuard.id * sleepyGuard.mostSleepingMinute
  }

  override fun solvePart2(input: String): Int = guards(input).maxBy { it.occurrencesOfMostSleepingMinute }
    .let { it.id * it.mostSleepingMinute }

  private fun guards(input: String): List<Guard> {
    val wholeRegex = ".*?:(\\d+)] (.*)$".toRegex()
    val guardRegex = """^Guard #(\d+)""".toRegex()
    val guards = mutableMapOf<Int, MutableList<Int>>()
    var currentGuard = 0
    var fellAsleepAt = 0
    input.lines().sorted().forEach { line ->
      val (minuteValue, rest) = wholeRegex.find(line)!!.destructured
      val minute = minuteValue.toInt()
      when (rest) {
        "falls asleep" -> {
          fellAsleepAt = minute
        }
        "wakes up" -> {
          guards.getOrPut(currentGuard) { mutableListOf() }
            .addAll(fellAsleepAt..<minute)
        }
        else -> {
          currentGuard = guardRegex.find(rest)!!.groupValues[1].toInt()
        }
      }
    }
    return guards.map { Guard(it.key, it.value) }
  }
}

private data class Guard(val id: Int, val sleepTimes: List<Int>) {

  val mostSleepingMinute: Int
  val occurrencesOfMostSleepingMinute: Int

  init {
    if (sleepTimes.isEmpty()) {
      mostSleepingMinute = 0
      occurrencesOfMostSleepingMinute = 0
    } else {
      val entry = sleepTimes.groupingBy { it }.eachCount()
        .maxBy { it.value }
      mostSleepingMinute = entry.key
      occurrencesOfMostSleepingMinute = entry.value
    }
  }
}
