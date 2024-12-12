package aoc.year2017

import aoc.library.Puzzle
import kotlin.String

object Day13 : Puzzle<Int, Int>(day = 13) {

  override fun solvePart1(input: String): Int = input.lines().map(Layer::parse)
    .sumOf { currentLayer ->
      currentLayer.tick(currentLayer.depth)
      if (currentLayer.position == 0) {
        currentLayer.depth * currentLayer.range
      } else {
        0
      }
    }

  override fun solvePart2(input: String): Int {
    val layers = input.lines().map(Layer::parse)
    var delay = 0
    delay@ while (true) {
      delay++
      for (layer in layers) {
        layer.reset()
        layer.tick(delay + layer.depth)
        if (layer.position == 0) {
          continue@delay
        }
      }
      return delay
    }
  }

  private data class Layer(val depth: Int, val range: Int, var position: Int = 0, var direction: Int = 1) {

    fun reset() {
      position = 0
      direction = 1
    }

    fun tick(times: Int) {
      repeat(times % ((range - 1) * 2)) {
        position += direction
        if (position == 0 || position == range - 1) {
          direction = direction.inv()
        }
      }
    }

    companion object {
      fun parse(input: String): Layer {
        val (depth, range) = input.split(": ").map(String::toInt)
        return Layer(depth = depth, range = range)
      }
    }
  }
}
