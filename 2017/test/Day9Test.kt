package aoc.year2017

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day9Test {

  @Test
  fun score() {
    Day9.score("{{<!!>},{<!!>},{<!!>},{<!!>}}") shouldBeExactly 9
    Day9.score("{{<a!>},{<a!>},{<a!>},{<ab>}}") shouldBeExactly 3
  }

  @Test
  fun garbageCount() {
    Day9.garbageCount("<>") shouldBeExactly 0
    Day9.garbageCount("<random characters>") shouldBeExactly 17
    Day9.garbageCount("<<<<>") shouldBeExactly 3
  }

  @Test
  fun part1() {
    Day9.solvePart1() shouldBeExactly 21037
  }

  @Test
  fun part2() {
    Day9.solvePart2() shouldBeExactly 9495
  }
}
