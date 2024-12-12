package aoc.year2023

import aoc.library.solvePart1
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day21Test {

  @Test
  fun positionAfter6Steps() {
    Day21.positionsAfterSteps(
      input = """
      ...........
      .....###.#.
      .###.##..#.
      ..#.#...#..
      ....#.#....
      .##..S####.
      .##..#...#.
      .......##..
      .##.#.####.
      .##..##.##.
      ...........
      """.trimIndent(),
      steps = 6,
    ) shouldBeExactly 16
  }

  @Test
  fun positionAfter10Steps() {
    Day21.positionsAfterSteps(
      input = """
      ...........
      .....###.#.
      .###.##..#.
      ..#.#...#..
      ....#.#....
      .##..S####.
      .##..#...#.
      .......##..
      .##.#.####.
      .##..##.##.
      ...........
      """.trimIndent(),
      steps = 10,
    ) shouldBeExactly 50
  }

  @Test
  fun positionAfter50Steps() {
    Day21.positionsAfterSteps(
      input = """
      ...........
      .....###.#.
      .###.##..#.
      ..#.#...#..
      ....#.#....
      .##..S####.
      .##..#...#.
      .......##..
      .##.#.####.
      .##..##.##.
      ...........
      """.trimIndent(),
      steps = 50,
    ) shouldBeExactly 1594
  }

  @Test
  fun part1() {
    Day21.solvePart1() shouldBeExactly 3660
  }
}
