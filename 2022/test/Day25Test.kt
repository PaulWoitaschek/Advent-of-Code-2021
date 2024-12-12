package aoc.year2022

import aoc.library.solvePart1
import aoc.year2022.Day25.SnafuNumber.Companion.toSnafuNumber
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.withClue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day25Test {

  private val numberMapping = mapOf(
    "12111" to 906L,
    "1" to 1L,
    "1=-0-2" to 1747L,
    "0" to 0L,
  ).mapKeys { Day25.SnafuNumber(it.key) }

  @Test
  fun part1TestInput() {
    Day25.solvePart1(
      """
1=-0-2
12111
2=0=
21
2=01
111
20012
112
1=-1=
1-12
12
1=
122
      """.trimIndent(),
    ) shouldBe "2=-1=0"
  }

  @Test
  fun part1() {
    Day25.solvePart1() shouldBe "2=0--0---11--01=-100"
  }

  @Test
  fun snafuNumberToDecimal() {
    assertSoftly {
      numberMapping.forEach { (snafuNumber, decimal) ->
        withClue("snafu=$snafuNumber, shouldBe decimal=$decimal") {
          snafuNumber.toDecimal() shouldBe decimal
        }
      }
    }
  }

  @Test
  fun decimalToSnafuNumber() {
    assertSoftly {
      numberMapping.forEach { (snafuNumber, decimal) ->
        withClue("snafu=$snafuNumber, shouldBe decimal=$decimal") {
          decimal.toSnafuNumber() shouldBe snafuNumber
        }
      }
    }
  }
}
