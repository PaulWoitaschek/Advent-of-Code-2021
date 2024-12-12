package aoc.year2023

import aoc.library.Puzzle

object Day2 : Puzzle<Int, Int>(2) {

  override fun solvePart1(input: String): Int = solve(input) { games ->
    val bagContents = mapOf("red" to 12, "green" to 13, "blue" to 14)
    games
      .filter { game ->
        game.shownCubes.all { round ->
          round.all { (shownColor, shownAmount) ->
            shownAmount <= bagContents.getValue(shownColor)
          }
        }
      }
      .sumOf { it.id }
  }

  override fun solvePart2(input: String): Int = solve(input) { games ->
    games.sumOf { game ->
      buildMap {
        game.shownCubes.forEach { sets ->
          sets.forEach { (color, amount) ->
            put(color, maxOf(getOrDefault(color, amount), amount))
          }
        }
      }.values.reduce(Int::times)
    }
  }

  private fun solve(
    input: String,
    solve: (List<Game>) -> Int,
  ): Int {
    val games = input.lines()
      .map { line ->
        val (gameIdValue, gameValue) = line.split(": ")
        Game(
          id = gameIdValue.removePrefix("Game ").toInt(),
          shownCubes = gameValue.split("; ").map { set ->
            set.split(", ")
              .associate { amountWithColor ->
                val (amount, color) = amountWithColor.split(" ")
                color to amount.toInt()
              }
          },
        )
      }
    return solve(games)
  }

  private data class Game(val id: Int, val shownCubes: List<Map<String, Int>>)
}
