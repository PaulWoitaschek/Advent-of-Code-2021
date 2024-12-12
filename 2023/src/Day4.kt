package aoc.year2023

import aoc.library.Puzzle
import kotlin.math.pow

object Day4 : Puzzle<Int, Int>(4) {

  override fun solvePart1(input: String): Int = input.lines()
    .map(Card::parse)
    .sumOf { card ->
      2.toDouble().pow(card.winningCount - 1).toInt()
    }

  override fun solvePart2(input: String): Int {
    val pool = input.lines().map(Card::parse)
    val hand = pool.associate { card ->
      card.id to 1
    }.toMutableMap()
    pool.forEach { card ->
      val winningCount = card.winningCount
      val cardsToAdd = hand.getValue(card.id)
      (card.id + 1..card.id + winningCount).forEach { nextCardId ->
        val cardCount = hand[nextCardId]
        if (cardCount != null) {
          hand[nextCardId] = cardCount + cardsToAdd
        }
      }
    }
    return hand.values.sum()
  }

  private data class Card(val id: Int, private val winningNumbers: List<Int>, private val ownNumbers: List<Int>) {

    val winningCount: Int = ownNumbers.count { it in winningNumbers }

    companion object {
      private val regex = """^Card.*?(\d+)(.*?)\|(.*?)$""".toRegex()
      fun parse(input: String): Card {
        val (id, winning, own) = regex.find(input)!!.destructured
        return Card(
          id = id.toInt(),
          winningNumbers = winning.split(" ").mapNotNull(String::toIntOrNull),
          ownNumbers = own.split(" ").mapNotNull(String::toIntOrNull),
        )
      }
    }
  }
}
