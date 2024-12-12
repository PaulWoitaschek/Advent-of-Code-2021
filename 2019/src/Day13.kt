package aoc.year2019

import aoc.library.Puzzle
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.math.sign

object Day13 : Puzzle<Int, Int>(13) {

  override fun solvePart1(input: String): Int = runBlocking {
    val computer = IntCodeComputer(
      instructions = input.split(",").map(String::toLong),
      inputs = Channel(),
      outputs = Channel(),
    )
    var blocks = 0
    launch {
      while (true) {
        computer.outputs.receive()
        computer.outputs.receive()
        val tileId = computer.outputs.receive().toInt()
        if (tileId == BLOCK) {
          blocks++
        }
      }
    }
    computer.run()
    computer.outputs.cancel()

    blocks
  }

  override fun solvePart2(input: String): Int = runBlocking {
    val inputs = Channel<Long>(capacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val computer = IntCodeComputer(
      instructions = input.split(",").map(String::toLong)
        .toMutableList()
        .apply {
          set(0, 2)
        },
      inputs = inputs,
      outputs = Channel(),
    )
    var score = 0
    launch {
      var positionOfPaddle = 0
      while (true) {
        val x = computer.outputs.receive().toInt()
        computer.outputs.receive()
        val value = computer.outputs.receive().toInt()
        if (x == -1) {
          score = value
        } else {
          if (value == PADDLE) {
            positionOfPaddle = x
          } else if (value == BALL) {
            inputs.trySend((x - positionOfPaddle).sign.toLong())
          }
        }
      }
    }
    computer.run()
    computer.outputs.cancel()
    score
  }

  private const val BLOCK = 2
  private const val PADDLE = 3
  private const val BALL = 4
}
