package aoc.year2022

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.withClue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day6Test {

  @Test
  fun day6Part1TestData() {
    mapOf(
      "mjqjpqmgbljsphdztnvjfqwrcgsmlb" to 7,
      "bvwbjplbgvbhsrlpgdmjqwftvncz" to 5,
      "nppdvjthqldpwncqszvftbrmjlhg" to 6,
      "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" to 10,
      "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" to 11,
    ).forEach { (input, expected) ->
      withClue("input=$input") {
        assertSoftly {
          Day6.solvePart1(input) shouldBe expected
        }
      }
    }
  }

  @Test
  fun day6() {
    Day6.solvePart1() shouldBe 1892
    Day6.solvePart2() shouldBe 2313
  }
}
