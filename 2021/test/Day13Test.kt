package aoc.year2021

import aoc.library.solvePart1
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day13Test {

  private val testInput = """
    6,10
    0,14
    9,10
    0,3
    10,4
    4,11
    6,0
    6,12
    4,1
    0,13
    10,12
    3,4
    3,0
    8,4
    1,10
    2,14
    8,10
    9,0

    fold along y=7
    fold along x=5
  """.trimIndent()

  @Test
  fun part1TestInput() {
    Day13.solvePart1(testInput) shouldBe 17
  }

  @Test
  fun part1() {
    Day13.solvePart1() shouldBe 684
  }

  @Test
  fun part2() {
    Day13.solvePart2(testInput) shouldBe """
            ╔═════╗
            ║█████║
            ║█   █║
            ║█   █║
            ║█   █║
            ║█████║
            ╚═════╝
    """.trimIndent()
  }
}
