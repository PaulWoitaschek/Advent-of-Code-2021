package aoc.year2024

import aoc.library.Puzzle
import kotlin.math.abs

object Day1 : Puzzle<Long, Long>(day = 1) {

  override fun solvePart1(input: String): Long {
    val (left, right) = parse(input)
    return left.sorted().zip(right.sorted()) { l, r ->
      abs(l - r)
    }.sum()
  }

  override fun solvePart2(input: String): Long {
    val (left, right) = parse(input)
    val grouping = right.groupingBy { it }.eachCount()
    return left.sumOf { it * grouping.getOrElse(it) { 0 } }
  }

  private fun parse(input: String): Pair<List<Long>, List<Long>> = input
    .lines()
    .map {
      it.substringBefore(" ").toLong() to it.substringAfterLast(" ").toLong()
    }
    .unzip()
}
