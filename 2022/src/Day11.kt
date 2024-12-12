package aoc.year2022

import aoc.library.Puzzle

object Day11 : Puzzle<Long, Long>(11) {

  override fun solvePart1(input: String): Long = solve(parseInput(input), rounds = 20) { it / 3 }

  override fun solvePart2(input: String): Long {
    val monkeys = parseInput(input)
    val divisor = monkeys.map { it.testDivision }.reduce(Int::times)
    return solve(monkeys, rounds = 10000) { it % divisor }
  }

  private fun solve(
    monkeys: List<Monkey>,
    rounds: Int,
    relax: (Long) -> Long,
  ): Long {
    repeat(rounds) {
      monkeys.forEach { monkey ->
        while (monkey.items.isNotEmpty()) {
          val item = relax(monkey.inspect(monkey.items.removeFirst()))
          monkey.inspectionCount++
          monkeys[monkey.willThrowItemTo(item)].items.add(item)
        }
      }
    }
    return monkeys.map { it.inspectionCount }.sorted().takeLast(2).reduce(Long::times)
  }

  private fun parseInput(input: String): List<Monkey> = input.split("\n\n").map(Monkey::parse)

  private data class Monkey(
    val items: MutableList<Long>,
    val operation: (Long, Long) -> Long,
    val operationCount: OperationValue,
    val testDivision: Int,
    val throwToIfTrue: Int,
    val throwToIfFalse: Int,
    var inspectionCount: Long = 0,
  ) {

    fun inspect(item: Long): Long = operation(
      item,
      when (operationCount) {
        OperationValue.Old -> item
        is OperationValue.Value -> operationCount.value
      },
    )

    fun willThrowItemTo(item: Long): Int = if (item % testDivision == 0L) throwToIfTrue else throwToIfFalse

    sealed interface OperationValue {
      data class Value(val value: Long) : OperationValue
      data object Old : OperationValue
      companion object {
        fun parse(value: String): OperationValue = if (value == "old") {
          Old
        } else {
          Value(value.toLong())
        }
      }
    }

    companion object {
      fun parse(input: String): Monkey {
        val lines = input.lines()
        return Monkey(
          items = lines[1].removePrefix("  Starting items: ")
            .split(",").map { it.trim().toLong() }.toMutableList(),
          operation = if ('*' in lines[2]) Long::times else Long::plus,
          operationCount = OperationValue.parse((lines[2].removePrefix("  Operation: new = old ").drop(2))),
          testDivision = lines[3].removePrefix("  Test: divisible by ").toInt(),
          throwToIfTrue = lines[4].removePrefix("    If true: throw to monkey ").toInt(),
          throwToIfFalse = lines[5].removePrefix("    If false: throw to monkey ").toInt(),
        )
      }
    }
  }
}
