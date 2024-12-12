package aoc.year2022

import aoc.library.Puzzle
import java.util.PriorityQueue
import kotlin.math.sign

object Day19 : Puzzle<Int, Int>(19) {

  override fun solvePart1(input: String): Int = parseInput(input).sumOf {
    it.largestGeodes(24) * it.id
  }

  override fun solvePart2(input: String): Int = parseInput(input)
    .take(3)
    .map { it.largestGeodes(32) }
    .reduce(Int::times)

  private fun Blueprint.largestGeodes(maxMinutes: Int): Int {
    var max = 0
    val potentialForMinutes = (1..maxMinutes).associateWith { days ->
      (days downTo 1)
        .reduce { acc, value ->
          acc + value
        }
    }

    fun Snapshot.potential(): Int {
      val remaining = maxMinutes - minute
      val geodeRobots = robots[Material.Geode] ?: 0
      val a = (remaining downTo 1).sumOf {
        geodeRobots * (it)
      }
      return (material[Material.Geode] ?: 0) + geodeRobots + a + (potentialForMinutes[remaining] ?: 0)
    }

    val queue = PriorityQueue<Snapshot>(
      compareByDescending {
        it.material[Material.Geode] ?: 0
      },
    )

    queue.add(
      Snapshot(
        minute = 1,
        robots = Material.entries.associateWith { if (it == Material.Ore) 1 else 0 },
        material = Material.entries.associateWith { 0 },
      ),
    )

    val maxNeededRobots = Material.entries.associateWith { material ->
      robotBlueprints.maxOf { it.costs[material] ?: 0 }
    }

    fun Snapshot.buildRobot(bluePrint: RobotBlueprint): Snapshot? {
      val remaining = maxMinutes - minute
      val produces = bluePrint.produces
      val shouldBuild = produces == Material.Geode ||
        robots.getOrDefault(produces, 0) < maxNeededRobots[produces]!!
      if (!shouldBuild) return null

      val missesRobots = bluePrint.costs.any { (ingredient, _) ->
        material[ingredient]!! == 0 && (robots[ingredient] ?: 0) == 0
      }
      if (missesRobots) return null

      val sleep = bluePrint.costs.maxOf { (ingredient, ingredientCount) ->
        val missing = (ingredientCount - material[ingredient]!!).coerceAtLeast(0)
        missing / (robots[ingredient] ?: 0) + (missing % (robots[ingredient] ?: 0)).sign
      } + 1
      if (sleep <= remaining) {
        return copy(
          minute = minute + sleep,
          material = material.mapValues {
            it.value + robots[it.key]!! * sleep - (bluePrint.costs[it.key] ?: 0)
          },
          robots = robots.mapValues {
            it.value + if (it.key == produces) 1 else 0
          },
        )
      }

      return null
    }

    while (queue.isNotEmpty()) {
      val state = queue.remove()!!

      max = maxOf(max, (state.material[Material.Geode] ?: 0) + (state.robots[Material.Geode] ?: 0))

      val remaining = maxMinutes - state.minute
      if (remaining <= 0) continue

      if (state.potential() <= max) {
        continue
      }

      robotBlueprints
        .mapNotNull(state::buildRobot)
        .ifEmpty { listOf(state.advanceTime(remaining)) }
        .also(queue::addAll)
    }

    return max
  }

  private fun parseInput(input: String): List<Blueprint> {
    val regex = "(\\d++)".toRegex()
    return input.lines().filter(String::isNotEmpty)
      .map {
        val numbers = regex.findAll(it).map { line ->
          line.groupValues[1].toInt()
        }.toList()
        Blueprint(
          id = numbers[0],
          robotBlueprints = listOf(
            RobotBlueprint(Material.Ore, costs = mapOf(Material.Ore to numbers[1])),
            RobotBlueprint(Material.Clay, costs = mapOf(Material.Ore to numbers[2])),
            RobotBlueprint(
              Material.Obsidian,
              costs = mapOf(
                Material.Ore to numbers[3],
                Material.Clay to numbers[4],
              ),
            ),
            RobotBlueprint(
              Material.Geode,
              costs = mapOf(
                Material.Ore to numbers[5],
                Material.Obsidian to numbers[6],
              ),
            ),
          ),
        )
      }
  }

  data class Snapshot(val minute: Int, val material: Map<Material, Int>, val robots: Map<Material, Int>) {

    fun advanceTime(minutes: Int): Snapshot = copy(
      minute = minute + minutes,
      material = material.mapValues {
        it.value + robots[it.key]!! * minutes
      },
    )
  }

  data class Blueprint(val id: Int, val robotBlueprints: List<RobotBlueprint>)

  data class RobotBlueprint(val produces: Material, val costs: Map<Material, Int>)

  enum class Material {
    Ore,
    Clay,
    Obsidian,
    Geode,
  }
}
