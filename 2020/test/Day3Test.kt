package aoc.year2020

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day3Test {

  @Test
  fun part1() {
    Day3.solvePart1() shouldBe 193
    Day3.solvePart1(
      """
..##.......
#...#...#..
.#....#..#.
..#.#...#.#
.#...##..#.
..#.##.....
.#.#.#....#
.#........#
#.##...#...
#...##....#
.#..#...#.#
      """.trimIndent(),
    ) shouldBe 7
  }

  @Test
  fun part2() {
    Day3.solvePart2() shouldBe 1355323200
    Day3.solvePart2(
      """
..##.......
#...#...#..
.#....#..#.
..#.#...#.#
.#...##..#.
..#.##.....
.#.#.#....#
.#........#
#.##...#...
#...##....#
.#..#...#.#
      """.trimIndent(),
    ) shouldBe 336
  }
}
