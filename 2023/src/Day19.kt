package aoc.year2023

import aoc.library.Puzzle

object Day19 : Puzzle<Int, Long>(day = 19) {

  override fun solvePart1(input: String): Int {
    val system = System.parse(input)
    return system.parts.sumOf { part ->
      system.process(WorkflowName.Input, part)
    }
  }

  override fun solvePart2(input: String): Long = System.parse(input).workflowCombinations()

  private data class System(val workflows: List<Workflow>, val parts: List<Parts>) {

    fun workflowCombinations(): Long {
      val parts: Map<PartId, IntRange> = "xmas".associate {
        PartId(it.toString()) to 1..4000
      }
      return findRanges(parts, WorkflowName.Input)
        .accepted
        .sumOf {
          it.values
            .map { range ->
              range.last.toLong() - range.first.toLong() + 1
            }
            .reduce(Long::times)
        }
    }

    private fun findRanges(
      parts: Map<PartId, IntRange>,
      workflowName: WorkflowName,
    ): Ranges {
      val workflow = workflows.first { it.name == workflowName }
      return workflow.rules.runningFold(
        Ranges(
          accepted = listOf(),
          pending = listOf(parts),
        ),
      ) { combinations, rule ->
        val accepted = combinations.accepted.toMutableList()
        val newPending = mutableListOf<Map<PartId, IntRange>>()
        combinations.pending.forEach { current ->
          when (rule.condition) {
            Condition.AlwaysTrue -> {
              when (rule.destination) {
                Destination.Accepted -> accepted.add(current)
                Destination.Rejected -> newPending.add(current)
                is Destination.ToWorkflow -> {
                  accepted.addAll(findRanges(current, rule.destination.workflow).accepted)
                }
              }
            }
            is Condition.Compare -> {
              val conditionValue = rule.condition.value
              val conditionPartId = rule.condition.partId
              val range = current.getValue(conditionPartId)
              val inRange = current.plus(
                conditionPartId to
                  if (rule.condition.greaterThan) {
                    maxOf(conditionValue + 1, range.first)..range.last
                  } else {
                    range.first..minOf(conditionValue - 1, range.last)
                  },
              )
              val outOfRange = current.plus(
                conditionPartId to
                  if (rule.condition.greaterThan) {
                    range.first..minOf(conditionValue, range.last)
                  } else {
                    maxOf(conditionValue, range.first)..range.last
                  },
              )
              when (rule.destination) {
                Destination.Accepted -> {
                  accepted += inRange
                  newPending += outOfRange
                }
                Destination.Rejected -> {
                  newPending += outOfRange
                }
                is Destination.ToWorkflow -> {
                  accepted += findRanges(
                    parts = inRange,
                    workflowName = rule.destination.workflow,
                  ).accepted
                  newPending += outOfRange
                }
              }
            }
          }
        }
        Ranges(accepted, newPending)
      }.last()
    }

    fun process(
      workflowName: WorkflowName,
      parts: Parts,
    ): Int {
      val workflow = workflows.first { it.name == workflowName }
      return when (val destination = workflow.process(parts)) {
        Destination.Accepted -> parts.parts.values.sum()
        Destination.Rejected -> 0
        is Destination.ToWorkflow -> process(destination.workflow, parts)
      }
    }

    companion object {
      fun parse(input: String): System {
        val (workflowValue, partsValue) = input.split("\n\n")
        return System(
          workflows = workflowValue.lines().map(Workflow::parse),
          parts = partsValue.lines().map(Parts::parse),
        )
      }
    }
  }

  private data class Parts(val parts: Map<PartId, Int>) {

    operator fun get(partId: PartId) = parts.getValue(partId)

    companion object {
      fun parse(input: String): Parts {
        val parts = input.removeSurrounding("{", "}")
          .split(",")
          .associate {
            val (id, value) = it.split("=")
            PartId(id) to value.toInt()
          }
        return Parts(parts)
      }
    }
  }

  @JvmInline
  private value class WorkflowName(val name: String) {

    companion object {
      val Input = WorkflowName("in")
    }
  }

  @JvmInline
  private value class PartId(val id: String)

  private data class Workflow(val name: WorkflowName, val rules: List<Rule>) {

    fun process(parts: Parts): Destination {
      val rule = rules.first { rule ->
        rule.evaluate(parts)
      }
      return rule.destination
    }

    companion object {
      fun parse(input: String): Workflow {
        val name = input.substringBefore('{')
        val rules = input.substringAfter('{').substringBefore('}')
          .split(",")
          .map(Rule::parse)
        return Workflow(WorkflowName(name), rules)
      }
    }
  }

  private data class Rule(val condition: Condition, val destination: Destination) {

    fun evaluate(parts: Parts): Boolean = when (condition) {
      Condition.AlwaysTrue -> true
      is Condition.Compare -> {
        if (condition.greaterThan) {
          parts[condition.partId] > condition.value
        } else {
          parts[condition.partId] < condition.value
        }
      }
    }

    companion object {
      fun parse(input: String): Rule = if (input.contains(":")) {
        val (conditionValue, destination) = input.split(":")
        val condition = Condition.parse(conditionValue)
        Rule(condition, Destination.parse(destination))
      } else {
        Rule(
          condition = Condition.AlwaysTrue,
          destination = Destination.parse(input),
        )
      }
    }
  }

  private sealed interface Destination {
    data object Accepted : Destination
    data object Rejected : Destination
    data class ToWorkflow(val workflow: WorkflowName) : Destination

    companion object {
      fun parse(input: String): Destination = when (input) {
        "A" -> Accepted
        "R" -> Rejected
        else -> ToWorkflow(WorkflowName(input))
      }
    }
  }

  private sealed interface Condition {
    data class Compare(val partId: PartId, val value: Int, val greaterThan: Boolean) : Condition
    data object AlwaysTrue : Condition
    companion object {
      fun parse(input: String): Condition {
        val (partId, value) = input.split('<', '>')
        return Compare(PartId(partId), value.toInt(), greaterThan = ">" in input)
      }
    }
  }

  private data class Ranges(val accepted: List<Map<PartId, IntRange>>, val pending: List<Map<PartId, IntRange>>)
}
