package aoc.year2018

import aoc.library.Puzzle
import kotlin.String

object Day8 : Puzzle<Int, Int>(day = 8) {

  override fun solvePart1(input: String): Int = parse(input).metaDataSum()

  override fun solvePart2(input: String): Int = parse(input).value()

  private fun parse(input: String): Node {
    val numbers = input.split(" ").map(String::toInt)
    var pointer = 0
    fun read(): Int = numbers[pointer++]

    fun readNode(): Node {
      val childNodeCount = read()
      val metaDataSize = read()
      val childNodes = (0 until childNodeCount).map { readNode() }
      val metaData = (0 until metaDataSize).map { read() }
      return Node(childNodes, metaData)
    }

    return readNode()
  }

  private data class Node(val nodes: List<Node>, val metaData: List<Int>) {

    fun metaDataSum(): Int = metaData.reduce(Int::plus) + nodes.sumOf { it.metaDataSum() }

    fun value(): Int = if (nodes.isEmpty()) {
      metaData.reduce(Int::plus)
    } else {
      metaData.fold(0) { sum, value ->
        sum + (nodes.getOrNull(value - 1)?.value() ?: 0)
      }
    }
  }
}
