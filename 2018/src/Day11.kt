package aoc.year2018

import aoc.library.Point
import aoc.library.Puzzle

object Day11 : Puzzle<String, Long>(day = 11) {
  override fun solvePart1(input: String): String {
    val serialNumber = input.toInt()
    val powerLevels = (1..300)
      .flatMap { x ->
        (1..300).map { y ->
          Point(x, y)
        }
      }
      .associateWith { powerLevel(it, serialNumber) }
    return (1..297)
      .flatMap { x ->
        (1..297).map { y ->
          Point(x, y)
        }
      }
      .associateWith { point ->
        (0..2)
          .flatMap { x ->
            (0..2).map { y ->
              Point(x, y)
            }
          }
          .map { point + it }
          .sumOf { powerLevels.getValue(it) }
      }
      .maxBy { it.value }.also { println(it) }
      .key
      .let { "${it.x},${it.y}" }
  }

  override fun solvePart2(input: String): Long {
    TODO()
  }

  fun powerLevel(
    coordinate: Point,
    serialNumber: Int,
  ): Int {
    val rackId = coordinate.x + 10
    var powerLevel = rackId * coordinate.y
    powerLevel += serialNumber
    powerLevel *= rackId
    if (powerLevel >= 100) {
      powerLevel = powerLevel.toString().dropLast(2).last().digitToInt()
    }
    powerLevel -= 5
    return powerLevel
  }
}
