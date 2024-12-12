package aoc.year2022

import aoc.library.Puzzle

object Day18 : Puzzle<Int, Int>(18) {

  override fun solvePart1(input: String): Int {
    val lava = parseInput(input)
    return lava.sumOf { cube ->
      cube.adjacent().count { it !in lava }
    }
  }

  override fun solvePart2(input: String): Int {
    val lava = parseInput(input)
    val xRange = lava.minOf { it.x - 1 }.rangeTo(lava.maxOf { it.x + 1 })
    val yRange = lava.minOf { it.y - 1 }.rangeTo(lava.maxOf { it.y + 1 })
    val zRange = lava.minOf { it.z - 1 }.rangeTo(lava.maxOf { it.z + 1 })

    val air = mutableSetOf<Cube>()
    val queue = ArrayDeque(listOf(Cube(xRange.first, yRange.first, zRange.first)))
    while (queue.isNotEmpty()) {
      val cube = queue.removeLast()
      if (!air.add(cube)) continue
      cube.adjacent()
        .filter { it !in air && it.x in xRange && it.y in yRange && it.z in zRange && it !in lava }
        .let(queue::addAll)
    }
    return air.sumOf { airCube ->
      airCube.adjacent().count { neighbor ->
        neighbor in lava
      }
    }
  }

  data class Cube(val x: Int, val y: Int, val z: Int) {
    fun adjacent() = listOf(
      copy(x = x + 1),
      copy(x = x - 1),
      copy(y = y + 1),
      copy(y = y - 1),
      copy(z = z + 1),
      copy(z = z - 1),
    )

    override fun toString(): String = "($x,$y,$z)"

    companion object {
      fun parse(input: String): Cube {
        val (x, y, z) = input.split(",").map(String::toInt)
        return Cube(x, y, z)
      }
    }
  }

  private fun parseInput(input: String): List<Cube> = input.lines().filter(String::isNotEmpty).map(Cube::parse)
}
