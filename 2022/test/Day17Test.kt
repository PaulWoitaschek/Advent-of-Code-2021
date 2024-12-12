package aoc.year2022

import aoc.library.solvePart1
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day17Test {

  @Test
  fun part1Test() {
    Day17.solvePart1(">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>") shouldBe 3068L
  }

  @Test
  fun part1() {
    Day17.solvePart1() shouldBe 3181L
  }

  @Test
  fun caveRocks() {
    val cave = Day17.Cave()
    cave.hasRock(0, 0) shouldBe false
    cave.putRock(0, 0)
    cave.hasRock(0, 0) shouldBe true
    cave.putRock(1, 1)
    cave.hasRock(0, 0) shouldBe true
    cave.hasRock(1, 1) shouldBe true

    cave.putRock(4, 2)
    cave.hasRock(4, 2) shouldBe true
    cave.hasRock(1, 1) shouldBe true
    cave.hasRock(1, 4) shouldBe false
  }

  @Test
  fun caveTop() {
    val cave = Day17.Cave()

    cave.putRock(0, 0)
    cave.top() shouldBe 0L

    cave.putRock(6, 0)
    cave.top() shouldBe 0L

    cave.putRock(6, 1)
    cave.top() shouldBe 1L

    cave.putRock(2, 4)
    cave.top() shouldBe 4

    cave.putRock(6, 5)
    cave.top() shouldBe 5L

    cave.putRock(6, 1)
    cave.top() shouldBe 5L

    cave.putRock(6, 7)
    cave.top() shouldBe 7L
  }
}
