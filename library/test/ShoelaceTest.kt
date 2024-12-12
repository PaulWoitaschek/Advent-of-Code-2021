package aoc.library

import io.kotest.matchers.longs.shouldBeExactly
import org.junit.jupiter.api.Test

class ShoelaceTest {

  @Test
  fun `test square perimeter`() {
    shoelace(
      listOf(
        Point(0, 0),
        Point(0, 10),
        Point(10, 10),
        Point(10, 0),
      ),
    ) shouldBeExactly 100
  }

  @Test
  fun `test triangle area`() {
    shoelace(
      listOf(
        Point(0, 0),
        Point(4, 10),
        Point(9, 0),
      ),
    ) shouldBeExactly 45
  }

  @Test
  fun `test pentagon area`() {
    shoelace(
      listOf(
        Point(0, 0),
        Point(2, 4),
        Point(4, 2),
        Point(4, -2),
        Point(2, -4),
      ),
    ) shouldBeExactly 20
  }

  @Test
  fun `test complex shape area`() {
    shoelace(
      listOf(
        Point(3, 4),
        Point(5, 11),
        Point(12, 8),
        Point(9, 5),
        Point(5, 6),
      ),
    ) shouldBeExactly 30
  }

  @Test
  fun `test closed square perimeter`() {
    shoelace(
      listOf(
        Point(0, 0),
        Point(0, 10),
        Point(10, 10),
        Point(10, 0),
        Point(0, 0),
      ),
    ) shouldBeExactly 100
  }
}
