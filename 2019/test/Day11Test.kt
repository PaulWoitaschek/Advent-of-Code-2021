package aoc.year2019

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day11Test {

  @Test
  fun part1() {
    Day11.solvePart1() shouldBeExactly 2211
  }

  @Test
  fun part2() {
    Day11.solvePart2() shouldBe """
        ╔═══════════════════════════════════════╗
        ║████ ████  ██  █  █ █  █ ████  ██   ██ ║
        ║█    █    █  █ █ █  █  █ █    █  █ █  █║
        ║███  ███  █    ██   █  █ ███  █    █   ║
        ║█    █    █    █ █  █  █ █    █ ██ █   ║
        ║█    █    █  █ █ █  █  █ █    █  █ █  █║
        ║████ █     ██  █  █  ██  ████  ███  ██ ║
        ╚═══════════════════════════════════════╝
    """.trimIndent()
  }
}
