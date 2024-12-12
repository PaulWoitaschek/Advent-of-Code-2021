package aoc.year2023

import aoc.library.Puzzle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking

object Day12 : Puzzle<Long, Long>(day = 12) {

  override fun solvePart1(input: String): Long = solve(input.lines())

  override fun solvePart2(input: String): Long = solve(
    input.lines()
      .map { line ->
        val (left, right) = line.split(" ")
        val newLeft = (0..4).joinToString(separator = "?") { left }
        val newRight = (0..4).joinToString(separator = ",") { right }
        "$newLeft $newRight"
      },
  )

  private fun solve(lines: List<String>): Long = runBlocking(Dispatchers.Default) {
    lines.map { line ->
      async {
        solveEntry(line)
      }
    }.awaitAll().sum()
  }

  private fun solveEntry(line: String): Long {
    val memo = mutableMapOf<Memo, Long>()
    fun visit(
      damages: List<Int>,
      recognized: List<Int>,
      springConditions: String,
    ): Long = memo.getOrPut(Memo(recognized, springConditions)) {
      if (springConditions.isEmpty()) {
        return@getOrPut if (recognized == damages) 1 else 0
      }
      if (recognized.size > damages.size) return@getOrPut 0
      recognized.forEachIndexed { index, i ->
        if (damages[index] != i) return@getOrPut 0
      }

      val first = springConditions.first()
      val group = springConditions.takeWhile { it == first }

      when (first) {
        '?' -> {
          listOf('.', '#').sumOf {
            visit(
              damages = damages,
              recognized = recognized,
              springConditions = it + springConditions.drop(1),
            )
          }
        }
        '.' -> visit(
          damages = damages,
          recognized = recognized,
          springConditions = springConditions.drop(group.length),
        )
        '#' -> {
          val rest = springConditions.drop(group.length)
          val after = rest.firstOrNull()
          if (after == '?') {
            listOf('.', '#').sumOf {
              visit(
                damages = damages,
                recognized = recognized,
                springConditions = springConditions.replaceFirst('?', it),
              )
            }
          } else {
            visit(
              damages = damages,
              recognized = recognized + group.length,
              springConditions = springConditions.drop(group.length),
            )
          }
        }
        else -> error("Invalid data")
      }
    }

    val (springConditions, damageMap) = line.split(" ")
    val pattern = damageMap.split(",").map(String::toInt)
    return visit(damages = pattern, recognized = emptyList(), springConditions = springConditions)
  }

  private data class Memo(val recognized: List<Int>, val remaining: String)
}
