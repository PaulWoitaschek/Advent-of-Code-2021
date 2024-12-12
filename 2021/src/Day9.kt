package aoc.year2021

import aoc.library.Point
import aoc.library.Puzzle

object Day9 : Puzzle<Long, Long>(9) {

  override fun solvePart1(input: String): Long = HeightMap.parse(input)
    .riskLevelSum()
    .toLong()

  override fun solvePart2(input: String): Long = HeightMap.parse(input)
    .multipliedSumOfThreeHighestBasins()
    .toLong()
}

private data class HeightMap(private val heights: List<List<Int>>) {

  private val allPoints: List<PointWithValue> = heights.flatMapIndexed { y, columns ->
    columns.mapIndexed { x, value ->
      PointWithValue(point = Point(x, y), value = value)
    }
  }

  fun riskLevelSum(): Int = lowPointCoordinates().sumOf { it.value + 1 }

  fun multipliedSumOfThreeHighestBasins(): Int = basins()
    .sortedDescending()
    .take(3)
    .reduce { acc, i -> acc * i }

  private fun basins(): List<Int> = lowPointCoordinates().map { basinCoordinates(it).count() }

  private fun lowPointCoordinates(): List<PointWithValue> = allPoints.filter { point ->
    point.surroundingPoints().all { it.value > point.value }
  }

  private fun basinCoordinates(from: PointWithValue): Set<PointWithValue> = mutableSetOf<PointWithValue>().also { set ->
    addBasinCoordinates(from, set)
  }

  private fun addBasinCoordinates(
    from: PointWithValue,
    result: MutableSet<PointWithValue>,
  ) {
    result += from
    from.surroundingPoints().forEach { surrounding ->
      if (surrounding.value < 9 && surrounding.value > from.value) {
        addBasinCoordinates(surrounding, result)
      }
    }
  }

  companion object {
    fun parse(input: String): HeightMap = HeightMap(
      input.lines()
        .map { line ->
          line.toList().map {
            it.digitToInt()
          }
        },
    )
  }

  private fun PointWithValue.surroundingPoints(): List<PointWithValue> = point.adjacentOrthogonal()
    .mapNotNull { adjacentPoint -> allPoints.find { it.point == adjacentPoint } }

  private data class PointWithValue(val point: Point, val value: Int)
}
