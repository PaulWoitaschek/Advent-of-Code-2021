package aoc.year2022

import aoc.library.Puzzle

object Day21 : Puzzle<Long, Long>(21) {

  override fun solvePart1(input: String): Long {
    val monkeys = parseInput(input)

    fun calculationElement(shout: Shout): CalculationElement = when (shout) {
      is Shout.Operation -> {
        CalculationElement.Operation(
          operator = shout.operator,
          a = calculationElement(monkeys.getValue(shout.a)),
          b = calculationElement(monkeys.getValue(shout.b)),
        )
      }
      is Shout.Value -> {
        CalculationElement.Value(shout.value)
      }
    }

    val calculationElement = calculationElement(monkeys.getValue("root"))

    fun reduce(element: CalculationElement): Long {
      return when (element) {
        is CalculationElement.Operation -> {
          val a = reduce(element.a)
          val b = reduce(element.b)
          return when (element.operator) {
            Operator.Plus -> Math.addExact(a, b)
            Operator.Times -> Math.multiplyExact(a, b)
            Operator.Div -> {
              check(a % b == 0L) { "Only even division supported for $a / $b" }
              a / b
            }
            Operator.Minus -> Math.subtractExact(a, b)
          }
        }
        is CalculationElement.Value -> element.value
      }
    }
    return reduce(calculationElement)
  }

  private fun parseInput(input: String): Map<String, Shout> = input.lines().filter(String::isNotEmpty)
    .associate { line ->
      val name = line.substringBefore(":")
      val value = line.substringAfter(": ")
      name to when {
        value.first().isDigit() -> Shout.Value(value.toLong())
        else -> {
          val a = value.substringBefore(" ")
          val b = value.substringAfterLast(" ")
          val operation = Operator.parse(value.substringAfter(" ").first())
          Shout.Operation(operation, a, b)
        }
      }
    }

  override fun solvePart2(input: String) = TODO()

  sealed interface CalculationElement {

    @JvmInline
    value class Value(val value: Long) : CalculationElement

    data class Operation(val operator: Operator, val a: CalculationElement, val b: CalculationElement) : CalculationElement
  }

  enum class Operator(val char: Char) {
    Plus('+'),
    Times('*'),
    Div('/'),
    Minus('-'),
    ;

    companion object {
      fun parse(input: Char) = entries.first { it.char == input }
    }
  }

  sealed interface Shout {
    @JvmInline
    value class Value(val value: Long) : Shout
    data class Operation(val operator: Operator, val a: String, val b: String) : Shout
  }
}
