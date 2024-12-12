package aoc.year2023

import aoc.library.Puzzle

object Day25 : Puzzle<Int, Nothing>(day = 25) {

  override fun solvePart1(input: String): Int {
    val connections = parse(input)
    val removal = sortByUsage(input)
    val elements = connections.flatMap { listOf(it.from, it.to) }.toSet()

    for (i in 0 until removal.size) {
      for (j in i + 1 until removal.size) {
        for (k in j + 1 until removal.size) {
          val firstRemovedConnection = removal[i]
          val rem = setOf(firstRemovedConnection, removal[j], removal[k])
          val removedConnections = removal - rem
          fun walk(
            from: String,
            visited: MutableSet<String>,
          ) {
            if (!visited.add(from)) {
              return
            }
            removedConnections.forEach { connection ->
              val next = connection.connect(from)
              if (next != null) {
                walk(next, visited)
              }
            }
          }

          val firstVisit = mutableSetOf<String>()
          val secondVisit = mutableSetOf<String>()
          walk(firstRemovedConnection.from, firstVisit)
          walk(firstRemovedConnection.to, secondVisit)
          if (secondVisit.size + firstVisit.size == elements.size) {
            check(firstVisit + secondVisit == elements)
            return firstVisit.size * secondVisit.size
          }
        }
      }
    }
    error("Not found")
  }

  private fun sortByUsage(input: String): List<Connection> {
    val connections = parse(input)
    val counter = mutableMapOf<Connection, Int>()
    fun visit(current: String) {
      val queue = mutableListOf(current)
      val visited = mutableSetOf<String>()
      while (queue.isNotEmpty()) {
        val element = queue.removeFirst()
        connections.forEach { connection ->
          val connected = connection.connect(element)
          if (connected != null) {
            if (visited.add(connected)) {
              counter[connection] = (counter[connection] ?: 0) + 1
              queue.add(connected)
            }
          }
        }
      }
    }
    connections.flatMap { listOf(it.from, it.to) }.toSet()
      .take(50)
      .forEach { visit(it) }
    return counter.toList().sortedByDescending { it.second }.map { it.first }
  }

  private fun parse(input: String): Set<Connection> {
    val connections = mutableListOf<List<String>>()
    input.lines().forEach { line ->
      val (left, right) = line.split(": ")
      val rightElements = right.split(" ")
      rightElements.forEach {
        connections.add(listOf(left, it))
      }
    }
    return connections
      .map {
        val sorted = it.sorted()
        Connection(sorted[0], sorted[1])
      }
      .toSet()
  }

  private data class Connection(val from: String, val to: String) {
    fun connect(value: String): String? = when (value) {
      from -> to
      to -> from
      else -> null
    }
  }

  override fun solvePart2(input: String): Nothing = error("Does not exist")
}
