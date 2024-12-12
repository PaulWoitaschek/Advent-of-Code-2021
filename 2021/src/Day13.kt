package aoc.year2021

import aoc.library.Point
import aoc.library.Puzzle
import aoc.library.printString
import kotlin.math.abs

object Day13 : Puzzle<Long, String>(13) {

  override fun solvePart1(input: String): Long {
    val (points, instructions) = parse(input)
    val firstInstruction = instructions.first()
    return points.map { it.followInstruction(firstInstruction) }
      .toSet()
      .count()
      .toLong()
  }

  override fun solvePart2(input: String): String {
    val (points, instructions) = parse(input)
    return instructions.fold(points) { grid, instruction ->
      grid.map { it.followInstruction(instruction) }.toSet()
    }.toSet().printString()
  }
}

private fun Point.followInstruction(instruction: FoldingInstruction): Point = if (instruction.foldVertically) {
  if (y <= instruction.coordinate) {
    this
  } else {
    Point(x, instruction.coordinate - abs(y - instruction.coordinate))
  }
} else {
  if (x <= instruction.coordinate) {
    this
  } else {
    Point(instruction.coordinate - abs(x - instruction.coordinate), y)
  }
}

private fun parse(input: String): Pair<Set<Point>, List<FoldingInstruction>> {
  val lines = input.lines()
  val points = lines.takeWhile { it.isNotEmpty() }
    .map {
      val (x, y) = it.split(",")
      Point(x.toInt(), y.toInt())
    }
    .toSet()
  val foldRegex = "fold along (.*?)=(.*)".toRegex()
  val foldInstructions = lines.dropWhile { !foldRegex.containsMatchIn(it) }
    .map {
      val result = foldRegex.find(it)!!
      val (axis, value) = result.groupValues.drop(1)
      FoldingInstruction(foldVertically = axis == "y", coordinate = value.toInt())
    }
  return points to foldInstructions
}

private data class FoldingInstruction(val foldVertically: Boolean, val coordinate: Int)
