package aoc.year2021

import aoc.library.Puzzle

object Day17 : Puzzle<Int, Int>(17) {

  override fun solvePart1(input: String): Int = allSuccessfulShots(input).maxOf { it.highestY }

  override fun solvePart2(input: String): Int = allSuccessfulShots(input).count()
}

private fun allSuccessfulShots(input: String): List<VelocityWithHighestY> {
  val targetArea = TargetArea.parse(input)
  return (targetArea.y.last - targetArea.x.last..targetArea.y.first + targetArea.x.last)
    .flatMap { yVelocity ->
      (1..targetArea.x.last).mapNotNull { xVelocity ->
        highestYIfHit(targetArea = targetArea, xVelocity = xVelocity, yVelocity = yVelocity)
      }
    }
}

private fun highestYIfHit(
  targetArea: TargetArea,
  xVelocity: Int,
  yVelocity: Int,
): VelocityWithHighestY? {
  var yMax = 0
  generateSequence(Probe(0, 0, xVelocity, yVelocity)) { it.afterStep() }
    .forEach { probe ->
      yMax = maxOf(yMax, probe.y)
      if (probe in targetArea) {
        return VelocityWithHighestY(xVelocity = xVelocity, yVelocity = yVelocity, highestY = yMax)
      } else if (probe.outOfRange(targetArea)) {
        return null
      }
    }
  error("Infinite sequence")
}

private data class VelocityWithHighestY(val xVelocity: Int, val yVelocity: Int, val highestY: Int)

private data class Probe(val x: Int, val y: Int, val xVelocity: Int, val yVelocity: Int) {

  fun outOfRange(of: TargetArea): Boolean = x > of.x.last || y < of.y.first

  fun afterStep(): Probe = Probe(
    x = x + xVelocity,
    y = y + yVelocity,
    xVelocity = xVelocity + when {
      xVelocity < 0 -> 1
      xVelocity > 0 -> -1
      else -> 0
    },
    yVelocity = yVelocity - 1,
  )
}

private data class TargetArea(val x: IntRange, val y: IntRange) {

  operator fun contains(probe: Probe): Boolean = probe.x in x && probe.y in y

  companion object {
    fun parse(input: String): TargetArea {
      val result = "target area: x=(.*?)\\.\\.(.*?), y=(.*?)\\.\\.(.*)".toRegex()
        .find(input)!!.groupValues.drop(1)
      return TargetArea(
        result[0].toInt()..result[1].toInt(),
        result[2].toInt()..result[3].toInt(),
      )
    }
  }
}
