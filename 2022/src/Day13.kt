package aoc.year2022

import aoc.library.Puzzle
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.int

object Day13 : Puzzle<Int, Int>(13) {

  override fun solvePart1(input: String): Int = input.split("\n\n").mapIndexedNotNull { index, group ->
    val (left, right) = group.lines().take(2).map { it.asJsonArray() }
    (index + 1).takeIf { order(left, right)!! <= 0 }
  }.reduce(Int::plus)

  override fun solvePart2(input: String): Int {
    val distressPackages = listOf("[[2]]", "[[6]]").map { it.asJsonArray() }
    val inputPackages = input.lines().filter(String::isNotBlank).map { it.asJsonArray() }
    val sortedPackages = (distressPackages + inputPackages).sortedWith { a, b -> order(a, b)!! }
    return distressPackages.map { sortedPackages.indexOf(it) + 1 }.reduce(Int::times)
  }

  private fun String.asJsonArray(): JsonArray = Json.decodeFromString(JsonArray.serializer(), this)

  private fun order(
    left: JsonElement,
    right: JsonElement,
  ): Int? {
    return if (left is JsonPrimitive && right is JsonPrimitive) {
      (left.int - right.int).takeUnless { it == 0 }
    } else if (left is JsonArray && right is JsonArray) {
      repeat(maxOf(left.size, right.size)) { index ->
        val leftValue = left.getOrNull(index)
        val rightValue = right.getOrNull(index)
        if (leftValue == null) {
          return Int.MIN_VALUE
        } else if (rightValue == null) {
          return Int.MAX_VALUE
        } else {
          val childInOrder = order(leftValue, rightValue)
          if (childInOrder != null) {
            return childInOrder
          }
        }
      }
      null
    } else if (left is JsonArray && right is JsonPrimitive) {
      order(left, JsonArray(listOf(right)))
    } else if (left is JsonPrimitive && right is JsonArray) {
      order(JsonArray(listOf(left)), right)
    } else {
      error("This can not be!")
    }
  }
}
