package aoc.year2021

import aoc.library.Puzzle
import kotlin.math.ceil
import kotlin.math.floor

object Day18 : Puzzle<Int, Int>(18) {

  override fun solvePart1(input: String): Int = input.lines().map(::SnailFishNumber)
    .reduce { a, b -> (a + b) }.magnitude()

  override fun solvePart2(input: String): Int {
    val numbers = input.lines().map(::SnailFishNumber)
    return numbers.maxOf { left ->
      numbers.filter { it != left }.maxOf { right ->
        (left + right).magnitude()
      }
    }
  }
}

@JvmInline
value class SnailFishNumber(val value: String) {

  operator fun plus(other: SnailFishNumber): SnailFishNumber = SnailFishNumber("[$value,${other.value}]").reduce()

  private fun indexOfExplodingCandidate(): IntRange? {
    var level = 0
    value.forEachIndexed { index, char ->
      when (char) {
        '[' -> level++
        ']' -> level--
      }
      if (level > 4) {
        val match = numberPairRegex.matchAt(value, index)
        if (match != null) {
          return match.range
        }
      }
    }
    return null
  }

  fun explode(): SnailFishNumber {
    val explodingRange = indexOfExplodingCandidate() ?: return this

    val (leftValue, rightValue) = value.substring(explodingRange).parseNumberPair()

    val rightPart = value.substring(startIndex = explodingRange.last + 1)
    val rightReplaced = multipleDigitsRegex.find(rightPart)?.let { match ->
      rightPart.replaceRange(match.range, (match.value.toInt() + rightValue).toString())
    } ?: rightPart

    val leftPart = value.substring(startIndex = 0, endIndex = explodingRange.first)
    val leftReplaced = multipleDigitsRegex.findAll(leftPart).lastOrNull()?.let { match ->
      leftPart.replaceRange(match.range, (match.value.toInt() + leftValue).toString())
    } ?: leftPart

    return SnailFishNumber(leftReplaced + "0" + rightReplaced)
  }

  fun split(): SnailFishNumber {
    val match = twoDigitNumberRegex.find(value) ?: return this
    val halfNumber = match.groupValues.single().toInt() / 2.0
    val first = floor(halfNumber).toInt()
    val second = ceil(halfNumber).toInt()
    return SnailFishNumber(value.replaceRange(match.range, "[$first,$second]"))
  }

  private fun reduce(): SnailFishNumber {
    var number = this
    while (true) {
      val exploded = number.explode()
      if (exploded != number) {
        number = exploded
        continue
      }
      val split = number.split()
      if (split != number) {
        number = split
        continue
      }
      return number
    }
  }

  fun magnitude(): Int {
    var result = value
    while (true) {
      val match = numberPairRegex.find(result)
      if (match != null) {
        val magnitude = match.groupValues[1].toInt() * 3 + match.groupValues[2].toInt() * 2
        result = result.replaceRange(match.range, magnitude.toString())
      } else {
        return result.toInt()
      }
    }
  }
}

private val multipleDigitsRegex = "\\d+".toRegex()
private val twoDigitNumberRegex = "\\d\\d+".toRegex()
private val numberPairRegex = "\\[(\\d+),(\\d+)]".toRegex()
private fun String.parseNumberPair(): Pair<Int, Int> = numberPairRegex.find(this)!!.groupValues
  .let { Pair(it[1].toInt(), it[2].toInt()) }
