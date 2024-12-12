package aoc.year2019

import aoc.year2019.IntCodeComputer.Mode.Immediate
import aoc.year2019.IntCodeComputer.Mode.Positional
import aoc.year2019.IntCodeComputer.Mode.Relative
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.channels.ReceiveChannel
import kotlin.math.pow

class IntCodeComputer(instructions: List<Long>, val inputs: ReceiveChannel<Long>, val outputs: Channel<Long> = Channel(UNLIMITED)) {

  val instructions: MutableMap<Long, Long> = instructions.withIndex().associate {
    it.index.toLong() to it.value
  }.toMutableMap()

  constructor(
    instructions: List<Long>,
    inputs: List<Long>,
  ) : this(
    instructions = instructions,
    inputs = Channel<Long>(UNLIMITED)
      .also { channel ->
        inputs.forEach {
          check(channel.trySend(it).isSuccess)
        }
      },
  )

  private var pointer = 0L
  private var relativeBase = 0L
  private var finished = false

  private val output = mutableListOf<Long>()

  val concatFullOutput: Long
    get() = output.joinToString("").toLong()
  val fullOutput: List<Long>
    get() = output.toList()
  val firstInstruction: Long
    get() = instructions.getValue(0)

  suspend fun run() {
    while (!finished) {
      runInstruction()
    }
  }

  private fun read(index: Long): Long {
    val value = instructions.getValue(pointer + index + 1)
    return when (mode(index)) {
      Positional -> value(value)
      Immediate -> value
      Relative -> instructions.getValue(value + relativeBase)
    }
  }

  private fun write(
    index: Long,
    value: Long,
  ) {
    val mode = mode(index)
    when (mode) {
      Immediate -> error("Writes are never positional")
      Positional -> {
        instructions[instructions.getValue(pointer + index + 1)] = value
      }
      Relative -> {
        instructions[instructions.getValue(pointer + index + 1) + relativeBase] = value
      }
    }
  }

  private fun mode(index: Long): Mode {
    val instructionValue = instructions.getValue(pointer)
    val divisor = 10.toFloat().pow(index.toInt() + 2)
    return when (val mode = (instructionValue / divisor % 10).toInt()) {
      0 -> Positional
      1 -> Immediate
      2 -> Relative
      else -> error("Invalid mode=$mode")
    }
  }

  private enum class Mode { Immediate, Positional, Relative; }

  private fun value(key: Long): Long = instructions[key] ?: 0L

  private suspend fun runInstruction() {
    when (val opCode = (instructions.getValue(pointer) % 100).toInt()) {
      1 -> {
        write(2, read(0) + read(1))
        pointer += 4
      }
      2 -> {
        write(2, read(0) * read(1))
        pointer += 4
      }
      3 -> {
        val received = inputs.receive()
        write(0, received)
        pointer += 2
      }
      4 -> {
        val o = read(0)
        output.add(o)
        outputs.send(o)
        pointer += 2
      }
      5 -> {
        if (read(0) != 0L) {
          pointer = read(1)
        } else {
          pointer += 3
        }
      }
      6 -> {
        if (read(0) == 0L) {
          pointer = read(1)
        } else {
          pointer += 3
        }
      }
      7 -> {
        val value: Long = if (read(0) < read(1)) 1 else 0
        write(2, value)
        pointer += 4
      }
      8 -> {
        val value: Long = if (read(0) == read(1)) 1 else 0
        write(2, value)
        pointer += 4
      }
      9 -> {
        relativeBase += read(0)
        pointer += 2
      }
      99 -> {
        finished = true
      }
      else -> error("Invalid opCode=$opCode")
    }
  }
}
