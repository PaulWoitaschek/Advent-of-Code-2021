package aoc.year2018

import aoc.utils.test
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day2Test {

  @Test
  fun part1Test() {
    Day2.solvePart1(
      """
      abcdef
      bababc
      abbcde
      abcccd
      aabcdd
      abcdee
      ababab
      """.trimIndent(),
    ) shouldBeExactly 12
  }

  @Test
  fun part1() {
    Day2.test(part1 = 9139)
  }

  @Test
  fun part2Test() {
    Day2.solvePart2(
      """
      abcde
      fghij
      klmno
      pqrst
      fguij
      axcye
      wvxyz
      """.trimIndent(),
    ) shouldBe "fgij"
  }

  @Test
  fun part2() {
    Day2.test(part2 = "uqcidadzwtnhsljvxyobmkfyr")
  }
}
