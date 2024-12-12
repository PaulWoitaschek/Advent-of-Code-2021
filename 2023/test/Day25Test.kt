package aoc.year2023

import aoc.library.solvePart1
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.Test

class Day25Test {

  @Test
  fun part1TestInput() {
    Day25.solvePart1(
      """
      jqt: rhn xhk nvd
      rsh: frs pzl lsr
      xhk: hfx
      cmg: qnr nvd lhk bvb
      rhn: xhk bvb hfx
      bvb: xhk hfx
      pzl: lsr hfx nvd
      qnr: nvd
      ntq: jqt hfx bvb xhk
      nvd: lhk
      lsr: lhk
      rzs: qnr cmg lsr rsh
      frs: qnr lhk lsr
      """.trimIndent(),
    ) shouldBeExactly 54
  }

  @Test
  fun part1() {
    Day25.solvePart1() shouldBeExactly 548960
  }
}
