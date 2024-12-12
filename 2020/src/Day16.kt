package aoc.year2020

import aoc.library.Puzzle

object Day16 : Puzzle<Int, Long>(16) {

  override fun solvePart1(input: String): Int {
    val parsed = Input.parse(input)
    return parsed.nearbyTickets.flatMap { it.values }
      .filter { value ->
        parsed.rules.none { rule ->
          value in rule
        }
      }
      .reduce { acc, i -> acc + i }
  }

  override fun solvePart2(input: String): Long {
    val parsed = Input.parse(input)
    val validNearbyTickets = parsed.nearbyTickets
      .filter { ticket ->
        ticket.values.all { value ->
          parsed.rules.any { rule ->
            value in rule
          }
        }
      }

    val remainingIndices = validNearbyTickets.first().values.indices.toMutableList()
    val remainingRules = parsed.rules.toMutableList()

    val association = mutableMapOf<Int, Rule>()
    while (remainingRules.isNotEmpty()) {
      val (index, rule) = remainingIndices.firstNotNullOf { index ->
        val match = remainingRules.singleOrNull { rule ->
          validNearbyTickets.all { ticket ->
            ticket.values[index] in rule
          }
        }
        match?.let { index to it }
      }
      association[index] = rule
      remainingIndices.remove(index)
      remainingRules.remove(rule)
    }

    return parsed.ownTicket.values
      .filterIndexed { index, _ ->
        association.getValue(index).label.startsWith("departure")
      }.map(Int::toLong).reduce(Long::times)
  }

  private data class Input(val rules: List<Rule>, val ownTicket: Ticket, val nearbyTickets: List<Ticket>) {
    companion object {
      fun parse(input: String): Input {
        val (rulesString, ownTicketString, nearbyString) = input.split("\n\n")
        val regex = "(.*?): (\\d+)-(\\d+) or (\\d+)-(\\d+)".toRegex()
        val rules = rulesString.lines()
          .map {
            val (label, firstRangeFrom, firstRangeTo, secondRangeFrom, secondRangeTo) = regex.find(it)!!.destructured.toList()
            Rule(
              label = label,
              firstRange = firstRangeFrom.toInt()..firstRangeTo.toInt(),
              secondRange = secondRangeFrom.toInt()..secondRangeTo.toInt(),
            )
          }
        val ownTicket = Ticket.parse(ownTicketString.lines().drop(1).single())
        val nearbyTickets = nearbyString.lines().drop(1).filter(String::isNotEmpty)
          .map(Ticket::parse)
        return Input(rules, ownTicket, nearbyTickets)
      }
    }
  }

  @JvmInline
  private value class Ticket(val values: List<Int>) {
    companion object {
      fun parse(input: String): Ticket = Ticket(input.split(",").map(String::toInt))
    }
  }

  private data class Rule(val label: String, val firstRange: IntRange, val secondRange: IntRange) {

    operator fun contains(value: Int): Boolean = value in firstRange || value in secondRange
  }
}
