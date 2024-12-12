package aoc.year2018

import aoc.library.Puzzle

object Day7 : Puzzle<String, Int>(day = 7) {

  override fun solvePart1(input: String): String {
    val values = solve(input, 1, 0)
    return values.toList().sortedBy { it.second }.map { it.first }.joinToString("")
  }

  override fun solvePart2(input: String): Int = solvePart2(input = input, workerCount = 5, additionalSeconds = 60)

  fun solvePart2(
    input: String,
    workerCount: Int,
    additionalSeconds: Int,
  ): Int = solve(
    input = input,
    workerCount = workerCount,
    additionalSeconds = additionalSeconds,
  ).values.max() - 1

  private fun solve(
    input: String,
    workerCount: Int,
    additionalSeconds: Int,
  ): MutableMap<Char, Int> {
    val regex = """^Step (.) must be finished before step (.) can begin\.$""".toRegex()
    val rules = input.lines()
      .map { line ->
        val (before, after) = regex.find(line)!!.destructured.toList().map { it.single() }
        Rule(before = before, after = after)
      }
    val steps = buildSet {
      rules.forEach {
        add(it.before)
        add(it.after)
      }
    }
    val prerequisites = steps.associateWith { step ->
      rules.mapNotNull {
        if (step == it.after) it.before else null
      }
    }

    val workers = (0 until workerCount).toList()
    val workerStates = workers.associateWith { 0 }.toMutableMap()
    val chosen = mutableMapOf<Char, Int>()
    var second = 0
    while (chosen.size != steps.size) {
      second++
      workers.forEach { worker ->
        val idleTime = workerStates[worker]!!
        if (idleTime <= second) {
          val step = steps
            .filter { it !in chosen }
            .filter { step ->
              prerequisites[step]!!.all { prerequisite ->
                second >= (chosen[prerequisite] ?: Int.MAX_VALUE)
              }
            }
            .minOrNull() ?: return@forEach
          val stepValue = additionalSeconds + (step - 'A') + 1
          workerStates[worker] = second + stepValue
          chosen[step] = second + stepValue
        }
      }
    }
    return chosen
  }

  private data class Rule(val before: Char, val after: Char)
}
