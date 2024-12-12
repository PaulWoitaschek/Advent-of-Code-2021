package aoc.year2023

import aoc.library.Point3
import aoc.library.Puzzle
import aoc.library.intersects

object Day22 : Puzzle<Int, Int>(day = 22) {

  override fun solvePart1(input: String): Int {
    val bricks = parse(input)
    move(bricks)
    val bricksWithSupporters = bricks.associateWith { brick ->
      bricks.filter {
        it != brick && it.topZ() == brick.bottomZ() - 1 && it.xyPointsIntersect(brick)
      }
    }
    val essentialSupporters = bricksWithSupporters.values.filter { it.size == 1 }.flatten().toSet()
    return bricks.size - essentialSupporters.size
  }

  override fun solvePart2(input: String): Int {
    val bricks = parse(input)
    return bricks.sumOf { toBeRemoved ->
      bricks.forEach { it.zOffset = 0 }
      move(bricks)
      val withBrickRemoved = bricks - toBeRemoved
      val originalOffsets = withBrickRemoved.map { it.bottomZ() }
      move(withBrickRemoved)
      val newOffsets = withBrickRemoved.map { it.bottomZ() }
      originalOffsets.zip(newOffsets).count { it.first != it.second }
    }
  }

  private fun parse(input: String): List<Brick> = input.lines().map(Brick::parse).sortedBy { it.bottomZ() }

  private fun move(bricks: List<Brick>) {
    bricks.forEach { candidate ->
      val highestSupportingBrick = bricks
        .filter { it != candidate && it.topZ() < candidate.bottomZ() && it.xyPointsIntersect(candidate) }
        .maxOfOrNull { it.topZ() }
      val newBottom = (highestSupportingBrick ?: 0) + 1
      candidate.zOffset += candidate.bottomZ() - newBottom
    }
  }

  private data class Brick(val from: Point3, val to: Point3) {

    var zOffset = 0

    fun topZ() = maxOf(from.z, to.z) - zOffset

    fun bottomZ() = minOf(from.z, to.z) - zOffset

    fun xyPointsIntersect(other: Brick): Boolean {
      val horizontal = from.y == to.y
      val otherHorizontal = other.from.y == other.to.y
      return when {
        horizontal && otherHorizontal -> {
          // both horizontal
          from.y == other.from.y && (from.x..to.x).intersects(other.from.x..other.to.x)
        }
        !horizontal && !otherHorizontal -> {
          // both vertical
          from.x == other.from.x && (from.y..to.y).intersects(other.from.y..other.to.y)
        }
        horizontal -> {
          // self horizontal, other vertical
          from.y in other.from.y..other.to.y && other.from.x in from.x..to.x
        }
        else -> {
          // self vertical, other horizontal
          from.x in other.from.x..other.to.x && other.from.y in from.y..to.y
        }
      }
    }

    companion object {
      fun parse(input: String): Brick {
        val (from, to) = input.split("~").map(Point3::parse)
        return Brick(from = from, to = to)
      }
    }
  }
}
