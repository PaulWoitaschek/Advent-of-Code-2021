package aoc.year2023

import aoc.library.Puzzle

object Day7 : Puzzle<Int, Int>(day = 7) {

  override fun solvePart1(input: String): Int = solve(input = input, accountForJokers = false)
  override fun solvePart2(input: String): Int = solve(input = input, accountForJokers = true)

  private fun solve(
    input: String,
    accountForJokers: Boolean,
  ): Int = input.lines()
    .map(Hand::parse)
    .sortedWith(HandComparator(accountForJokers))
    .mapIndexed { index, hand ->
      val rank = index + 1
      rank * hand.bid
    }
    .sum()

  private data class Hand(val cards: String, val bid: Int) {
    companion object {
      fun parse(input: String): Hand {
        val (cards, bid) = input.split(" ")
        return Hand(cards = cards, bid = bid.toInt())
      }
    }
  }

  private class HandComparator(private val accountForJokers: Boolean) : Comparator<Hand> {

    private val cardRanks = if (accountForJokers) {
      "J23456789TQKA"
    } else {
      "23456789TJQKA"
    }.reversed().withIndex().associate { it.value to it.index }

    override fun compare(
      o1: Hand,
      o2: Hand,
    ): Int = compareValuesBy(
      o2.cards,
      o1.cards,
      { it.type() },
      { cardRanks.getValue(it[0]) },
      { cardRanks.getValue(it[1]) },
      { cardRanks.getValue(it[2]) },
      { cardRanks.getValue(it[3]) },
      { cardRanks.getValue(it[4]) },
    )

    private val types = mutableMapOf<String, Type>()

    private fun String.type(): Type = types.getOrPut(this) {
      val groupingValues = groupingBy { it }.eachCount()
        .toMutableMap()
        .apply {
          if (accountForJokers) {
            val jokers = remove('J') ?: 0
            if (jokers == 5) return Type.FiveOfAKind
            val best = maxBy { it.value }
            this[best.key] = best.value + jokers
          }
        }
        .values
      val max = groupingValues.max()
      if (max == 5) return Type.FiveOfAKind
      if (max == 4) return Type.FourOfAKind
      if (3 in groupingValues && 2 in groupingValues) {
        return Type.FullHouse
      }
      if (max == 3) return Type.ThreeOfAKind
      if (groupingValues.count { it == 2 } == 2) return Type.TwoPair
      if (max == 2) return Type.OnePair
      return Type.HighCard
    }
  }

  private enum class Type {
    FiveOfAKind,
    FourOfAKind,
    FullHouse,
    ThreeOfAKind,
    TwoPair,
    OnePair,
    HighCard,
  }
}
