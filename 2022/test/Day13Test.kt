package aoc.year2022

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day13Test {

  @Test
  fun part1TestInput() {
    Day13.solvePart1(
      """
[1,1,3,1,1]
[1,1,5,1,1]

[[1],[2,3,4]]
[[1],4]

[9]
[[8,7,6]]

[[4,4],4,4]
[[4,4],4,4,4]

[7,7,7,7]
[7,7,7]

[]
[3]

[[[]]]
[[]]

[1,[2,[3,[4,[5,6,7]]]],8,9]
[1,[2,[3,[4,[5,6,0]]]],8,9]
      """.trimIndent(),
    ) shouldBe 13
  }

  @Test
  fun part1() {
    Day13.solvePart1() shouldBe 6568
  }

  @Test
  fun part2Test() {
    Day13.solvePart2(
      """
[1,1,3,1,1]
[1,1,5,1,1]

[[1],[2,3,4]]
[[1],4]

[9]
[[8,7,6]]

[[4,4],4,4]
[[4,4],4,4,4]

[7,7,7,7]
[7,7,7]

[]
[3]

[[[]]]
[[]]

[1,[2,[3,[4,[5,6,7]]]],8,9]
[1,[2,[3,[4,[5,6,0]]]],8,9]
      """.trimIndent(),
    ) shouldBe 140
  }

  @Test
  fun part2() {
    Day13.solvePart2() shouldBe 19493
  }
}
