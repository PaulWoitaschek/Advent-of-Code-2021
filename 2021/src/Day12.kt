package aoc.year2021

import aoc.library.Puzzle

object Day12 : Puzzle<Long, Long>(12) {

  override fun solvePart1(input: String): Long = Passage.parse(input, allowTwoSmallCaves = false).pathsCount()

  override fun solvePart2(input: String): Long = Passage.parse(input, allowTwoSmallCaves = true).pathsCount()
}

private typealias Path = List<Segment>

private data class Passage(private val connections: List<SegmentConnection>, private val allowTwoSmallCaves: Boolean) {

  fun pathsCount(): Long = findPaths().size.toLong()

  private fun findPaths(): List<Path> = mutableListOf<Path>()
    .also { collectPaths(result = it, currentRoute = listOf(Segment.Start)) }

  private fun collectPaths(
    result: MutableList<Path>,
    currentRoute: Path,
  ) {
    validConnections(currentRoute = currentRoute).forEach { to ->
      val newRoute = currentRoute + to
      if (to == Segment.End) {
        result += newRoute
      } else {
        collectPaths(result = result, currentRoute = newRoute)
      }
    }
  }

  private fun validConnections(currentRoute: Path): Path = connections
    .mapNotNull { connection ->
      connection.connectionTo(currentRoute.last())
        .takeUnless { it == Segment.Start }
        ?.takeIf { to ->
          if (to is Segment.Cave && to.isSmallCave) {
            if (!allowTwoSmallCaves || currentRoute.hasDuplicatedSmallCaves()) {
              to !in currentRoute
            } else {
              true
            }
          } else {
            true
          }
        }
    }

  private fun Path.hasDuplicatedSmallCaves(): Boolean {
    val smallCaves = filter { it is Segment.Cave && it.isSmallCave }
    return smallCaves.toSet().size != smallCaves.size
  }

  companion object {
    fun parse(
      input: String,
      allowTwoSmallCaves: Boolean,
    ): Passage = Passage(
      connections = input.lines().map(SegmentConnection.Companion::parse),
      allowTwoSmallCaves = allowTwoSmallCaves,
    )
  }
}

private sealed interface Segment {

  data object Start : Segment

  data class Cave(val name: String) : Segment {
    val isSmallCave: Boolean = name.all(Char::isLowerCase)
  }

  data object End : Segment

  companion object {
    fun parse(value: String): Segment = when (value) {
      "start" -> Start
      "end" -> End
      else -> Cave(value)
    }
  }
}

private data class SegmentConnection(val from: Segment, val to: Segment) {

  fun connectionTo(segment: Segment): Segment? = when (segment) {
    from -> to
    to -> from
    else -> null
  }

  companion object {
    fun parse(input: String): SegmentConnection {
      val (from, to) = input.split("-")
      return SegmentConnection(
        from = Segment.parse(from),
        to = Segment.parse(to),
      )
    }
  }
}
