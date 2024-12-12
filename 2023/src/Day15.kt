package aoc.year2023

import aoc.library.Puzzle

object Day15 : Puzzle<Int, Int>(day = 15) {

  override fun solvePart1(input: String): Int = input.split(",").sumOf(::hash)

  override fun solvePart2(input: String): Int = distributeLenses(input).toList()
    .sumOf { (boxNumber, box) ->
      box.toList().withIndex().sumOf {
        val slotNumber = it.index + 1
        val focalLength = it.value.second
        focalLength * slotNumber * (boxNumber + 1)
      }
    }

  private fun distributeLenses(input: String): Map<Int, Map<String, Int>> {
    val boxes = mutableMapOf<Int, MutableMap<String, Int>>()
    input.split(",")
      .forEach { value ->
        val (label, right) = value.split('=', '-')
        val box = boxes.getOrPut(hash(label)) { mutableMapOf() }
        if (right.isNotEmpty()) {
          // equals
          box[label] = right.toInt()
        } else {
          // dash
          box.remove(label)
        }
      }
    return boxes
  }

  fun hash(code: String): Int = code.fold(0) { value, char ->
    (value + char.code) * 17 % 256
  }
}
