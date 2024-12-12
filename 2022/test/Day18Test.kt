package aoc.year2022

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day18Test {

  @Test
  fun part1TestInput() {
    Day18.solvePart1(
      """
2,2,2
1,2,2
3,2,2
2,1,2
2,3,2
2,2,1
2,2,3
2,2,4
2,2,6
1,2,5
3,2,5
2,1,5
2,3,5
      """.trimIndent(),
    ) shouldBe 64
  }

  @Test
  fun part1RegularInput() {
    Day18.solvePart1() shouldBe 4370
  }

  @Test
  fun part2TestInput() {
    Day18.solvePart2(
      """
2,2,2
1,2,2
3,2,2
2,1,2
2,3,2
2,2,1
2,2,3
2,2,4
2,2,6
1,2,5
3,2,5
2,1,5
2,3,5
      """.trimIndent(),
    ) shouldBe 58
  }

  @Test
  fun part2RegularInput() {
    Day18.solvePart2() shouldBe 2458
  }
}
