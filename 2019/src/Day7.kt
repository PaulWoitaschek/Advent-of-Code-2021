package aoc.year2019

import aoc.library.Puzzle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object Day7 : Puzzle<Long, Long>(7) {

  override fun solvePart1(input: String): Long {
    val values = input.split(",").map(String::toLong)
    var max = 0L
    settingsCombinations(0..4).forEach { settings ->
      var previousOutput = 0L
      settings.forEach {
        previousOutput = calculate(values, listOf(it.toLong(), previousOutput))
      }
      max = maxOf(max, previousOutput)
    }
    return max
  }

  private fun calculate(
    values: List<Long>,
    inputs: List<Long>,
  ): Long {
    val computer = IntCodeComputer(values, inputs.map { it })
    runBlocking { computer.run() }
    return computer.fullOutput.last()
  }

  override fun solvePart2(input: String): Long = runBlocking(Dispatchers.Default) {
    val values = input.split(",").map(String::toLong)
    var max = 0L
    settingsCombinations(5..9).forEach { settings ->
      fun Channel<Long>.withInitialValue(vararg initial: Int): ReceiveChannel<Long> {
        @OptIn(ExperimentalCoroutinesApi::class)
        return produce {
          initial.forEach {
            send(it.toLong())
          }
          consumeEach {
            send(it)
          }
        }
      }

      val eOutput = Channel<Long>(Channel.RENDEZVOUS)
      val a = IntCodeComputer(values, eOutput.withInitialValue(settings[0], 0))
      val b = IntCodeComputer(values, a.outputs.withInitialValue(settings[1]))
      val c = IntCodeComputer(values, b.outputs.withInitialValue(settings[2]))
      val d = IntCodeComputer(values, c.outputs.withInitialValue(settings[3]))
      val e = IntCodeComputer(values, d.outputs.withInitialValue(settings[4]), eOutput)
      coroutineScope {
        launch { a.run() }
        launch { b.run() }
        launch { c.run() }
        launch { d.run() }
        launch { e.run() }
      }
      listOf(a, b, c, d, e).forEach {
        it.inputs.cancel()
        it.outputs.cancel()
      }
      val value = e.fullOutput.last()
      max = maxOf(max, value)
    }
    max
  }

  private fun settingsCombinations(range: IntRange): List<List<Int>> {
    val options = range.toList()
    check(options.size == 5)
    val combinations = mutableListOf<List<Int>>()
    repeat(5) { a ->
      repeat(5) { b ->
        repeat(5) { c ->
          repeat(5) { d ->
            repeat(5) { e ->
              val settings = setOf(options[a], options[b], options[c], options[d], options[e])
              if (settings.size == 5) {
                combinations.add(settings.toList())
              }
            }
          }
        }
      }
    }
    return combinations
  }
}
