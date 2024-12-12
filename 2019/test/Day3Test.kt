package aoc.year2019

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day3Test {

  @Test
  fun solvePart1() {
    Day3.solvePart1() shouldBeExactly 217
  }

  @Test
  fun solvePart2() {
    Day3.solvePart2() shouldBeExactly 3454
  }

  @Test
  fun day3Part1() {
    Day3.solvePart1(
      """
      R75,D30,R83,U83,L12,D49,R71,U7,L72
      U62,R66,U55,R34,D71,R55,D58,R83
      """.trimIndent(),
    ) shouldBeExactly 159

    Day3.solvePart1(
      """
    R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51
    U98,R91,D20,R16,D67,R40,U7,R15,U6,R7
      """.trimIndent(),
    ) shouldBeExactly 135
  }

  @Test
  fun day3Part2() {
    Day3.solvePart2(
      """
      R75,D30,R83,U83,L12,D49,R71,U7,L72
      U62,R66,U55,R34,D71,R55,D58,R83
      """.trimIndent(),
    ) shouldBeExactly 610

    Day3.solvePart2(
      """
    R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51
    U98,R91,D20,R16,D67,R40,U7,R15,U6,R7
      """.trimIndent(),
    ) shouldBeExactly 410
  }
}
