package aoc.year2022

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day19Test {

  @Test
  fun part1TestInput() {
    Day19.solvePart1(
      """
Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.
Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay. Each geode robot costs 3 ore and 12 obsidian.
      """.trimIndent(),
    ) shouldBe 33
  }

  @Test
  fun part1RegularInput() {
    Day19.solvePart1() shouldBe 1565
  }

  @Test
  @Disabled("Too slow")
  fun part2RegularInput() {
    Day19.solvePart2() shouldBe 10672
  }

  @Test
  @Disabled("Too slow")
  fun part2TestInput() {
    val part2Test = 62 * 56
    Day19.solvePart2(
      """
Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.
Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay. Each geode robot costs 3 ore and 12 obsidian.
      """.trimIndent(),
    ) shouldBe part2Test
  }
}
