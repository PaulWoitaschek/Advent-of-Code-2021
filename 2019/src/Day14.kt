package aoc.year2019

import aoc.library.Puzzle
import kotlin.math.absoluteValue
import kotlin.math.ceil

object Day14 : Puzzle<Long, Long>(14) {

  override fun solvePart1(input: String): Long = orePrice(reactions(input), 1)

  override fun solvePart2(input: String): Long {
    val reactions = reactions(input)
    val fuelCapacity = 1000000000000
    var low = 0L
    var high = fuelCapacity
    while (low <= high) {
      val mid = (low + high) / 2
      if (orePrice(reactions, mid) < fuelCapacity) {
        if (low == mid) {
          return low
        }
        low = mid
      } else {
        high = mid
      }
    }
    return low
  }

  private fun orePrice(
    reactions: List<Reaction>,
    fuel: Long,
  ): Long {
    val balance = mutableMapOf("FUEL" to -fuel)
    while (true) {
      val missing = balance.firstNotNullOfOrNull {
        if (it.key != ORE && it.value < 0) {
          it.key
        } else {
          null
        }
      } ?: break

      val reaction = reactions.single { it.output.name == missing }
      val neededReactions = ceil(
        balance.getValue(missing).absoluteValue.toDouble() / reaction.output.amount,
      ).toLong()
      reaction.inputs.forEach { productInput ->
        balance[productInput.name] = balance.getOrDefault(productInput.name, 0) - productInput.amount * neededReactions
      }
      balance[reaction.output.name] = balance.getOrDefault(reaction.output.name, 0) + reaction.output.amount * neededReactions
    }
    return -balance.getValue(ORE)
  }

  private fun reactions(input: String): List<Reaction> {
    val reactions = input.lines()
      .map { line ->
        val (inputs, output) = line.split(" => ")
        val inputProducts = inputs.split(", ").map(Product::parse)
        val outputProduct = Product.parse(output)
        Reaction(inputProducts, outputProduct)
      }
    return reactions
  }

  private data class Reaction(val inputs: List<Product>, val output: Product)

  private data class Product(val name: String, val amount: Int) {
    companion object {
      fun parse(input: String): Product {
        val (amount, name) = input.split(" ")
        return Product(name = name, amount = amount.toInt())
      }
    }
  }
}

private const val ORE = "ORE"
