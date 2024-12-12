package aoc.library

import io.kotest.matchers.longs.shouldBeExactly
import org.junit.jupiter.api.Test

class LcmTest {

  @Test
  fun `lcm of 3 and 4`() {
    lcm(listOf(3, 4)) shouldBeExactly 12
  }

  @Test
  fun `lcm of prime numbers 5 and 7`() {
    lcm(listOf(5, 7)) shouldBeExactly 35
  }

  @Test
  fun `lcm of a single number 9`() {
    lcm(listOf(9)) shouldBeExactly 9
  }

  @Test
  fun `lcm of 6 and 8 with common factor`() {
    lcm(listOf(6, 8)) shouldBeExactly 24
  }

  @Test
  fun `lcm of multiple numbers 4, 6, 8`() {
    lcm(listOf(4, 6, 8)) shouldBeExactly 24
  }

  @Test
  fun `lcm of large numbers 20 and 30`() {
    lcm(listOf(20, 30)) shouldBeExactly 60
  }

  @Test
  fun `lcm of 1 and 11`() {
    lcm(listOf(1, 11)) shouldBeExactly 11
  }

  @Test
  fun `lcm with zero included`() {
    lcm(listOf(0, 5)) shouldBeExactly 0
  }

  @Test
  fun `lcm of negative and positive numbers -3 and 4`() {
    lcm(listOf(-3, 4)) shouldBeExactly 12
  }
}
