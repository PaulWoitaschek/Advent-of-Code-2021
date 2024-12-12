@file:Suppress("ConvertCallChainIntoSequence")

package aoc.year2021

import aoc.library.Puzzle

object Day4 : Puzzle<Long, Long>(4) {

  override fun solvePart1(input: String): Long {
    val game = Game.parse(input)
    val allDraws = game.allDraws
    val boards = game.boards
    (allDraws.indices).forEach { round ->
      val draws = allDraws.take(round)
      boards.forEach { board ->
        if (board.hasWon(draws)) {
          return board.score(draws)
        }
      }
    }
    error("No winners!")
  }

  override fun solvePart2(input: String): Long {
    val game = Game.parse(input)
    val allDraws = game.allDraws
    var boards = game.boards
    var lastWinner: Board? = null
    var lastDraw: List<Int> = allDraws
    (allDraws.indices).forEach { round ->
      val draws = allDraws.take(round)
      val winnersOfRound = boards.filter { it.hasWon(draws) }
      if (winnersOfRound.isNotEmpty()) {
        boards = boards - winnersOfRound.toSet()
        lastWinner = winnersOfRound.last()
        lastDraw = draws
      }
    }
    return checkNotNull(lastWinner) { "No winners!" }
      .score(lastDraw)
  }
}

private data class Game(val allDraws: List<Int>, val boards: List<Board>) {

  companion object {
    fun parse(input: String): Game {
      val lines = input.lines()
      val draws = lines.first().split(",").map { it.toInt() }

      val boards = lines.drop(1)
        .filter { it.isNotEmpty() }
        .map { line ->
          line.split("\\s+".toRegex())
            .filter { it.isNotBlank() }
            .map { it.toInt() }
        }
        .chunked(Board.SIZE)
        .map { Board(it) }
      return Game(draws, boards)
    }
  }
}

private data class Board(val numbers: List<List<Int>>) {

  private val rows: List<List<Int>> = numbers
  private val columns: List<List<Int>>

  init {
    columns = mutableListOf()
    (0 until SIZE).forEach { i ->
      columns.add(numbers.map { it[i] })
    }
  }

  init {
    require(numbers.size == SIZE)
    numbers.forEach {
      require(it.size == SIZE)
    }
  }

  fun hasWon(draws: List<Int>): Boolean = rows.any { it.hasWon(draws) } || columns.any { it.hasWon(draws) }

  fun score(draws: List<Int>): Long = (
    numbers.flatten()
      .filter { it !in draws }
      .sum() * draws.last()
    )
    .toLong()

  private fun List<Int>.hasWon(draws: List<Int>): Boolean = all { it in draws }

  companion object {

    const val SIZE = 5
  }
}
