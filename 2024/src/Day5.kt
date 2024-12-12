package aoc.year2024

import aoc.library.Puzzle

object Day5 : Puzzle<Int, Int>(day = 5) {

  override fun solvePart1(input: String): Int {
    val (pages, comparator) = parse(input)
    return pages
      .filter { it.sortedWith(comparator) == it }
      .sumOf { it[it.count() / 2] }
  }

  override fun solvePart2(input: String): Int {
    val (pages, comparator) = parse(input)
    return pages
      .filter { it.sortedWith(comparator) != it }
      .map { it.sortedWith(comparator) }
      .sumOf { it[it.count() / 2] }
  }

  private fun parse(input: String): Pair<List<List<Int>>, Comparator<Int>> {
    val (orderingRulesValue, pagesValue) = input.split("\n\n")
    return getPages(pagesValue) to comparator(orderingRulesValue)
  }

  private fun getPages(pagesValue: String) = pagesValue.lines().map { page ->
    page.split(",").map(String::toInt)
  }

  private fun comparator(orderingRulesValue: String): Comparator<Int> {
    val orderingRules = mutableMapOf<Pair<Int, Int>, Int>()
    orderingRulesValue.lines().forEach {
      val (l, r) = it.split("|").map(String::toInt)
      orderingRules[l to r] = -1
      orderingRules[r to l] = 1
    }
    return Comparator { o1, o2 ->
      orderingRules.getValue(o1 to o2)
    }
  }
}
