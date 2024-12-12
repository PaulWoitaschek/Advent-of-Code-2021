package aoc.year2022

import aoc.library.solvePart1
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day21Test {

  @Test
  fun part1() {
    Day21.solvePart1() shouldBe 291425799367130
    Day21.solvePart1(
      """
root: pppw + sjmn
dbpl: 5
cczh: sllz + lgvd
zczc: 2
ptdq: humn - dvpt
dvpt: 3
lfqf: 4
humn: 5
ljgn: 2
sjmn: drzm * dbpl
sllz: 4
pppw: cczh / lfqf
lgvd: ljgn * ptdq
drzm: hmdt - zczc
hmdt: 32
      """.trimIndent(),
    ) shouldBe 152
  }
}
