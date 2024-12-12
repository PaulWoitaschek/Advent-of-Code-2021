package aoc.year2019

import aoc.library.Direction
import aoc.library.Point
import aoc.library.Puzzle
import aoc.library.move
import aoc.library.printString
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object Day11 : Puzzle<Int, String>(11) {

  override fun solvePart1(input: String): Int = process(input, 0).size

  override fun solvePart2(input: String): String {
    val coloredArea = process(input, 1)
    return coloredArea.filterValues { it == Color.White }.keys.printString()
  }

  private fun process(
    input: String,
    initialInput: Int,
  ): Map<Point, Color> = runBlocking {
    val inputs = Channel<Long>(UNLIMITED)
      .also { it.send(initialInput.toLong()) }

    val computer = IntCodeComputer(input.split(",").map(String::toLong), inputs)
    val coloredArea = mutableMapOf<Point, Color>()
    val colorJob = launch {
      var robotPosition = Point(0, 0)
      var facing = Direction.Up
      while (true) {
        val colorValue = computer.outputs.receive()
        val color = Color.parse(colorValue)
        val turnLeft = computer.outputs.receive() == 0L
        coloredArea[robotPosition] = color
        facing = if (turnLeft) facing.counterClockwise() else facing.clockwise()
        robotPosition = robotPosition.move(facing)

        inputs.send(
          if (coloredArea[robotPosition] == Color.White) 1 else 0,
        )
      }
    }
    computer.run()
    colorJob.cancel()

    coloredArea
  }

  enum class Color {
    White,
    Black,
    ;

    companion object {
      fun parse(value: Long): Color = when (value) {
        0L -> Black
        1L -> White
        else -> error("Invalid color=$value")
      }
    }
  }
}
