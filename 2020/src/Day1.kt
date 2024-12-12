package aoc.year2020

import aoc.library.Puzzle

object Day1 : Puzzle<Long, Long>(1) {

  override fun solvePart1(input: String): Long = solve(input) { it.permutations2() }
  override fun solvePart2(input: String): Long = solve(input) { it.permutations3() }

  private fun solve(
    input: String,
    permutations: (List<Long>) -> Sequence<List<Long>>,
  ): Long = input.lines().filter(String::isNotEmpty).map { it.toLong() }
    .let(permutations)
    .first { it.sum() == 2020L }
    .reduce(Long::times)

  private fun <T> List<T>.permutations3(): Sequence<List<T>> = sequence {
    forEach { a ->
      forEach { b ->
        forEach { c ->
          yield(listOf(a, b, c))
        }
      }
    }
  }

  private fun <T> List<T>.permutations2(): Sequence<List<T>> = sequence {
    forEach { a ->
      forEach { b ->
        yield(listOf(a, b))
      }
    }
  }
}
