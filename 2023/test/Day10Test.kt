package aoc.year2023

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day10Test {

  @Test
  fun part1() {
    Day10.solvePart1() shouldBeExactly 6815
  }

  @Test
  fun part1TestInput1() {
    Day10.solvePart1(
      """
      ..F7.
      .FJ|.
      SJ.L7
      |F--J
      LJ...
      """.trimIndent(),
    ) shouldBeExactly 8
  }

  @Test
  fun part1TestInput2() {
    Day10.solvePart1(
      """
      .....
      .S-7.
      .|.|.
      .L-J.
      .....
      """.trimIndent(),
    ) shouldBeExactly 4
  }

  @Test
  fun part1TestInput3() {
    Day10.solvePart1(
      """
      F-7..
      L-J..
      .S-7.
      .|.|.
      .L-J.
      .....
      """.trimIndent(),
    ) shouldBeExactly 4
  }

  @Test
  fun part2() {
    Day10.solvePart2() shouldBeExactly 269
  }

  @Test
  fun part2TestInput() {
    Day10.solvePart2(
      """
      .F----7F7F7F7F-7....
      .|F--7||||||||FJ....
      .||.FJ||||||||L7....
      FJL7L7LJLJ||LJ.L-7..
      L--J.L7...LJS7F-7L7.
      ....F-J..F7FJ|L7L7L7
      ....L7.F7||L7|.L7L7|
      .....|FJLJ|FJ|F7|.LJ
      ....FJL-7.||.||||...
      ....L---J.LJ.LJLJ...
      """.trimIndent(),
    ) shouldBeExactly 8
  }
}
