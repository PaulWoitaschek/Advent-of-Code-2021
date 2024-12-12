package aoc.year2023

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.longs.shouldBeExactly
import org.junit.jupiter.api.Test

class Day12Test {

  private val testInput = """
      ???.### 1,1,3
      .??..??...?##. 1,1,3
      ?#?#?#?#?#?#?#? 1,3,1,6
      ????.#...#... 4,1,1
      ????.######..#####. 1,6,5
      ?###???????? 3,2,1
  """.trimIndent()

  @Test
  fun part1() {
    Day12.solvePart1() shouldBeExactly 6949
  }

  @Test
  fun part1TestInput() {
    Day12.solvePart1(testInput) shouldBeExactly 21
  }

  @Test
  fun part2() {
    Day12.solvePart2() shouldBeExactly 51456609952403
  }

  @Test
  fun part2TestInput() {
    Day12.solvePart2(testInput) shouldBeExactly 525152
  }
}
