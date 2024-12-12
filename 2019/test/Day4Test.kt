package aoc.year2019

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.withClue
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day4Test {

  @Test
  fun part1() {
    Day4.solvePart1("402328-864247") shouldBeExactly 454
  }

  @Test
  fun part2() {
    Day4.solvePart2("402328-864247") shouldBeExactly 288
  }

  @Test
  fun validPasswordPart1() {
    assertSoftly {
      mapOf(
        111111 to true,
        223450 to false,
        123789 to false,
      ).forEach { (password, validPassword) ->
        withClue("password=$password") {
          Day4.isValidPasswordPart1(password) shouldBe validPassword
        }
      }
    }
  }

  @Test
  fun validPasswordPart2() {
    //  assertSoftly {
    mapOf(
      112233 to true,
      123444 to false,
      111122 to true,
    ).forEach { (password, validPassword) ->
      withClue("password=$password") {
        Day4.isValidPasswordPart2(password) shouldBe validPassword
      }
      //   }
    }
  }
}
