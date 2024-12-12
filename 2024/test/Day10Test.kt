package aoc.year2024

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day10Test {

  @Test
  fun part1TC1() {
    Day10.solvePart1(
      """
    ...0...
    ...1...
    ...2...
    6543456
    7.....7
    8.....8
    9.....9
      """.trimIndent(),
    ) shouldBeExactly 2
  }

  @Test
  fun part1TC2() {
    Day10.solvePart1(
      """
      ..90..9
      ...1.98
      ...2..7
      6543456
      765.987
      876....
      987....
      """.trimIndent(),
    ) shouldBeExactly 4
  }

  @Test
  fun part1TC3() {
    Day10.solvePart1(
      """
      10..9..
      2...8..
      3...7..
      4567654
      ...8..3
      ...9..2
      .....01
      """.trimIndent(),
    ) shouldBeExactly 3
  }

  @Test
  fun part1TC4() {
    Day10.solvePart1(
      """
      89010123
      78121874
      87430965
      96549874
      45678903
      32019012
      01329801
      10456732
      """.trimIndent(),
    ) shouldBeExactly 36
  }

  @Test
  fun part1() {
    Day10.solvePart1() shouldBe 459
  }

  @Test
  fun part2TestInput() {
    Day10.solvePart2(
      """
      89010123
      78121874
      87430965
      96549874
      45678903
      32019012
      01329801
      10456732
      """.trimIndent(),
    ) shouldBeExactly 81
  }

  @Test
  fun part2() {
    Day10.solvePart2() shouldBeExactly 1034
  }
}
