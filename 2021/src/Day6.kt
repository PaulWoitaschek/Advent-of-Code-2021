@file:Suppress("ConvertCallChainIntoSequence")

package aoc.year2021

import aoc.library.Puzzle

object Day6 : Puzzle<Long, Long>(6) {

  override fun solvePart1(input: String): Long = totalFishesAfterDays(input, days = 80)

  override fun solvePart2(input: String): Long = totalFishesAfterDays(input, days = 256)
}

private fun totalFishesAfterDays(
  input: String,
  days: Int,
): Long = generateSequence(Ocean.parse(input)) { it.afterOneDay() }
  .take(days + 1).last().totalFishes

@JvmInline
private value class BreedTime(private val value: Int) {

  val willBreedToday: Boolean get() = value == 0

  fun afterNextDay(): BreedTime = if (willBreedToday) {
    AFTER_BREEDING
  } else {
    BreedTime(value - 1)
  }

  companion object {
    val FOR_NEW_FISH = BreedTime(8)
    private val AFTER_BREEDING = BreedTime(6)
  }
}

private data class Ocean(private val fishesPerBreedTime: Map<BreedTime, Long>) {

  val totalFishes: Long get() = fishesPerBreedTime.values.sum()

  fun afterOneDay(): Ocean {
    val result = mutableMapOf<BreedTime, Long>()
    fishesPerBreedTime.forEach { (breedTime, fishCount) ->
      if (breedTime.willBreedToday) {
        result[BreedTime.FOR_NEW_FISH] = result.getOrElse(BreedTime.FOR_NEW_FISH) { 0L } + fishCount
      }
      val afterNextDay = breedTime.afterNextDay()
      result[afterNextDay] = result.getOrElse(afterNextDay) { 0L } + fishCount
    }
    return Ocean(result)
  }

  companion object {
    fun parse(input: String): Ocean {
      val fishesPerBreedTime = input.lines()
        .single()
        .split(",")
        .map(String::toInt)
        .map(::BreedTime)
        .groupBy { it }
        .mapValues { it.value.size.toLong() }
      return Ocean(fishesPerBreedTime)
    }
  }
}
