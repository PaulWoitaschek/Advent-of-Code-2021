package aoc.year2017

import aoc.library.Puzzle
import kotlin.math.absoluteValue

object Day11 : Puzzle<Int, Int>(day = 11) {

  override fun solvePart1(input: String): Int {
    var distance = 0
    walkGrid(input) { distance = it }
    return distance
  }

  override fun solvePart2(input: String): Int {
    var max = 0
    walkGrid(input) { distance ->
      max = maxOf(distance, max)
    }
    return max
  }

  private fun walkGrid(
    input: String,
    onStep: (Int) -> Unit,
  ) {
    val steps = input.split(",")
    var x = 0
    var y = 0
    steps.forEach { step ->
      if ("e" in step) x++
      if ("w" in step) x--
      if ("n" in step) {
        y -= if (step.length == 1) 2 else 1
      }
      if ("s" in step) {
        y += if (step.length == 1) 2 else 1
      }
      onStep((x.absoluteValue + y.absoluteValue) / 2)
    }
  }
}
