package aoc.year2019

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day1Test {

  @Test
  fun part1() {
    Day1.solvePart1() shouldBeExactly 3353880
  }

  @Test
  fun part2() {
    Day1.solvePart2() shouldBeExactly 5027950
  }

  @Test
  fun fuelForMass() {
    assertSoftly {
      mapOf(
        12 to 2,
        14 to 2,
        1969 to 654,
        100756 to 33583,
      ).forEach { (mass, fuel) ->
        Day1.fuelForMass(mass) shouldBe fuel
      }
    }
  }

  @Test
  fun fuelForMassWithFuelTakingMass() {
    assertSoftly {
      mapOf(
        14 to 2,
        1969 to 966,
        100756 to 50346,
      ).forEach { (mass, fuel) ->
        Day1.fuelForMassWithFuelTakingMass(mass) shouldBe fuel
      }
    }
  }
}
