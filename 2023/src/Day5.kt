package aoc.year2023

import aoc.library.Puzzle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking

object Day5 : Puzzle<Long, Long>(day = 5) {

  override fun solvePart1(input: String): Long {
    val almanac = Almanac.parse(input)
    return almanac.numbers.minOf {
      almanac.findLocation(it, Element.Seed)
    }
  }

  override fun solvePart2(input: String): Long = runBlocking(Dispatchers.Default) {
    val almanac = Almanac.parse(input)
    almanac.numbers.chunked(2)
      .map { it[0]..<it[0] + it[1] }.map { range ->
        async {
          sequence {
            yieldAll(range)
          }.minOf { almanac.findLocation(it, Element.Seed) }
        }
      }.awaitAll().min()
  }

  private enum class Element {
    Seed,
    Soil,
    Fertilizer,
    Water,
    Light,
    Temperature,
    Humidity,
    Location,
  }

  private data class Almanac(val numbers: List<Long>, val elementMaps: List<ElementMap>) {

    // optimization
    private val elementLookup = elementMaps.associateBy { it.from }

    fun findLocation(
      number: Long,
      element: Element,
    ): Long {
      if (element == Element.Location) {
        return number
      }
      val mapping = elementLookup[element]!!

      var minValue = Long.MAX_VALUE
      for (range in mapping.ranges) {
        if (number in range.sourceRangeStart..<range.sourceRangeStart + range.rangeLength) {
          val diff = number - range.sourceRangeStart
          minValue = minOf(range.destinationRangeStart + diff)
        }
      }
      return findLocation(
        number = if (minValue == Long.MAX_VALUE) number else minValue,
        element = mapping.to,
      )
    }

    companion object {
      fun parse(input: String): Almanac {
        val split = input.split("\n\n")
        val seeds = split.first()
          .removePrefix("seeds: ")
          .split(" ")
          .map(String::toLong)
        val maps = split.drop(1).map(ElementMap::parse)
        return Almanac(seeds, maps)
      }
    }
  }

  private data class ElementMap(val from: Element, val to: Element, val ranges: List<ElementRange>) {

    companion object {
      private val elementRegex = "(.*?)-to-(.*?) map:".toRegex()
      fun parse(input: String): ElementMap {
        val lines = input.lines()
        val (from, to) = elementRegex.find(lines[0])!!.destructured
        val ranges = lines.drop(1).map(ElementRange::parse)
        return ElementMap(
          from = Element.entries.first { it.name.equals(from, ignoreCase = true) },
          to = Element.entries.first { it.name.equals(to, ignoreCase = true) },
          ranges = ranges,
        )
      }
    }
  }

  private data class ElementRange(val destinationRangeStart: Long, val sourceRangeStart: Long, val rangeLength: Long) {
    companion object {
      fun parse(input: String): ElementRange {
        val numbers = input.split(" ").map(String::toLong)
        return ElementRange(
          destinationRangeStart = numbers[0],
          sourceRangeStart = numbers[1],
          rangeLength = numbers[2],
        )
      }
    }
  }
}
