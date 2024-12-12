package aoc.year2022

import aoc.library.Point
import aoc.library.Puzzle
import kotlin.math.absoluteValue

class Day15(private val lineToScan: Int, private val rangeMax: Int) : Puzzle<Int, Long>(15) {

  override fun solvePart1(input: String): Int {
    val sensorsWithBeacons = parseInput(input)
    val minX = sensorsWithBeacons.minOf {
      (-it.sensor.x.absoluteValue) - it.sensor.manhattanDistanceTo(it.closestBeacon)
    }
    val maxX = sensorsWithBeacons.maxOf {
      it.sensor.x.absoluteValue + it.sensor.manhattanDistanceTo(it.closestBeacon)
    }
    return (minX..maxX).count { x ->
      val point = Point(x, lineToScan)
      when {
        sensorsWithBeacons.any { it.sensor == point } -> {
          false
        }
        sensorsWithBeacons.any { it.closestBeacon == point } -> {
          false
        }
        else -> {
          sensorsWithBeacons
            .any {
              it.distanceToClosestBeacon >= it.sensor.manhattanDistanceTo(point)
            }
        }
      }
    }
  }

  override fun solvePart2(input: String): Long {
    val sensorsWithBeacons = parseInput(input)
    val range = 0..rangeMax
    return sensorsWithBeacons.asSequence()
      .flatMap { sensorWithBeacon ->
        val distance = sensorWithBeacon.distanceToClosestBeacon + 1
        (-distance..distance).asSequence().flatMap { dx ->
          val dy = distance - dx.absoluteValue
          val sensor = sensorWithBeacon.sensor
          listOf(
            Point(x = sensor.x + dx, y = sensor.y - dy),
            Point(x = sensor.x + dx, y = sensor.y + dy),
          )
        }
      }
      .filter { it.x in range && it.y in range }
      .first { point ->
        sensorsWithBeacons.none {
          it.sensor.manhattanDistanceTo(point) < it.distanceToClosestBeacon + 1
        }
      }
      .let { it.x.toLong() * 4000000 + it.y.toLong() }
  }

  data class SensorWithBeacon(val sensor: Point, val closestBeacon: Point) {
    val distanceToClosestBeacon = sensor.manhattanDistanceTo(closestBeacon)
  }

  private fun parseInput(input: String): List<SensorWithBeacon> {
    val regex = "Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)".toRegex()
    return input.lines()
      .filter { it.isNotEmpty() }
      .map {
        val (sensorX, sensorY, beaconX, beaconY) = regex.find(it)!!.destructured
          .toList().map(String::toInt)
        SensorWithBeacon(Point(sensorX, sensorY), Point(beaconX, beaconY))
      }
  }
}
