package aoc.year2022

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day20Test {

  @Test
  fun testPart1Test() {
    Day20.solvePart1(
      """
1
2
-3
3
-2
0
4
      """.trimIndent(),
    ) shouldBe 3L
  }

  @Test
  fun testPart1() {
    Day20.solvePart1() shouldBe 19559L
  }

  @Test
  fun testPart2Test() {
    Day20.solvePart2(
      """
1
2
-3
3
-2
0
4
      """.trimIndent(),
    ) shouldBe 1623178306L
  }

  @Test
  fun testPart2() {
    Day20.solvePart2() shouldBe 912226207972L
  }
}
