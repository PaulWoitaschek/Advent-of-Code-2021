package aoc.year2021

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day10Test {

  private val testInput = """
    [({(<(())[]>[[{[]{<()<>>
    [(()[<>])]({[<{<<[]>>(
    {([(<{}[<>[]}>{[]{[(<()>
    (((({<>}<{<{<>}{[]{[]{}
    [[<[([]))<([[{}[[()]]]
    [{[{({}]{}}([{[{{{}}([]
    {<[[]]>}<{[{[{[]{()[[[]
    [<(<(<(<{}))><([]([]()
    <{([([[(<>()){}]>(<<{{
    <{([{{}}[<[[[<>{}]]]>[]]
  """.trimIndent()

  @Test
  fun part1() {
    Day10.solvePart1() shouldBe 364389
  }

  @Test
  fun part2() {
    Day10.solvePart2() shouldBe 2870201088
  }

  @Test
  fun part1TestInput() {
    Day10.solvePart1(testInput) shouldBe 26397
  }

  @Test
  fun part2TestInput() {
    Day10.solvePart2(testInput) shouldBe 288957
  }
}
