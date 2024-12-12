package aoc.year2020

import aoc.library.Puzzle

object Day14 : Puzzle<Long, Long>(day = 14) {
  override fun solvePart1(input: String): Long {
    val memory = mutableMapOf<Int, Long>()
    val memoryRegex = """mem\[(\d+)] = (\d+)""".toRegex()
    var mask = ""
    input.lines().forEach { line ->
      if (line.startsWith("mask = ")) {
        mask = line.removePrefix("mask = ")
      } else {
        val (address, value) = memoryRegex.find(line)!!.destructured.toList().map(String::toInt)
        memory[address] = value.toString(2).padStart(mask.length, '0')
          .zip(mask) { valueChar, maskChar ->
            if (maskChar != 'X') maskChar else valueChar
          }.joinToString("").toLong(2)
      }
    }
    return memory.values.sum()
  }

  override fun solvePart2(input: String): Long {
    TODO()
  }
}
