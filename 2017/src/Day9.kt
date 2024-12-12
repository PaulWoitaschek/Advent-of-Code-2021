package aoc.year2017

import aoc.library.Puzzle

object Day9 : Puzzle<Int, Int>(day = 9) {

  override fun solvePart1(input: String): Int = input.lines().sumOf { score(it) }

  override fun solvePart2(input: String): Int = input.lines().sumOf { garbageCount(it) }

  fun garbageCount(input: String): Int {
    var gc = 0
    input.clean { gc += it }
    return gc
  }

  fun score(input: String): Int {
    val cleaned = input.clean()
    val parent = Node(parent = null)
    var current = parent
    cleaned.forEach { char ->
      when (char) {
        '{' -> {
          val new = Node(parent = current)
          current.children.add(new)
          current = new
        }
        '}' -> {
          current = current.parent!!
        }
      }
    }

    return parent.score()
  }

  private class Node(val parent: Node?, val children: MutableList<Node> = mutableListOf()) {

    fun level(): Int = generateSequence(parent) { it.parent }.count()

    fun score(): Int = level() + children.sumOf { it.score() }
  }

  private fun String.clean(onGarbageFound: (Int) -> Unit = {}): String = removeCancelled().removeGarbage(onGarbageFound)

  private fun String.removeCancelled(): String {
    val exclamationIndex = indexOf('!')
    return if (exclamationIndex == -1) {
      this
    } else {
      removeRange(exclamationIndex, exclamationIndex + 2)
        .removeCancelled()
    }
  }

  private fun String.removeGarbage(onGarbageFound: (Int) -> Unit = {}): String {
    val garbageOpenIndex = indexOf('<')
    return if (garbageOpenIndex == -1) {
      this
    } else {
      val garbageCloseIndex = indexOf('>')
      onGarbageFound(garbageCloseIndex - garbageOpenIndex - 1)
      removeRange(garbageOpenIndex, garbageCloseIndex + 1)
        .removeGarbage(onGarbageFound)
    }
  }
}
