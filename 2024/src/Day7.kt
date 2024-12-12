package aoc.year2024

import aoc.library.Puzzle

object Day7 : Puzzle<Long, Long>(day = 7) {

  override fun solvePart1(input: String): Long = solve(input, listOf(Operator.Add, Operator.Multiply))

  override fun solvePart2(input: String): Long = solve(input, listOf(Operator.Add, Operator.Multiply, Operator.Concat))

  private fun solve(
    input: String,
    operators: List<Operator>,
  ): Long {
    val numberRegex = """(\d+)""".toRegex()
    return input.lines()
      .map { line ->
        numberRegex.findAll(line).map { it.value.toLong() }.toList()
      }
      .filter {
        val solution = it.first()
        val elements = it.drop(1)
        isSolvable(
          solution = solution,
          operators = operators,
          elements = elements,
          index = 1,
          sum = elements.first(),
        )
      }
      .sumOf { it.first() }
  }

  private fun isSolvable(
    solution: Long,
    operators: List<Operator>,
    elements: List<Long>,
    sum: Long,
    index: Int,
  ): Boolean {
    if (index >= elements.size) {
      return sum == solution
    }
    // performance optimization
    if (sum > solution) return false

    return operators.any { operator ->
      val nextElement = elements[index]
      val newSum = when (operator) {
        Operator.Add -> sum + nextElement
        Operator.Multiply -> sum * nextElement
        Operator.Concat -> (sum.toString() + nextElement.toString()).toLong()
      }
      isSolvable(
        solution = solution,
        operators = operators,
        elements = elements,
        sum = newSum,
        index = index + 1,
      )
    }
  }

  private enum class Operator { Add, Multiply, Concat }
}
