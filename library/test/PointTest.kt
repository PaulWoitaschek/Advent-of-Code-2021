package aoc.library

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PointTest {

  @Test
  fun testPrintString() {
    setOf(
      Point(0, 0),
      Point(1, 0),
      Point(2, 0),
      Point(2, 1),
    ).printString() shouldBe """
        ╔═══╗
        ║███║
        ║  █║
        ╚═══╝
    """.trimIndent()
  }

  @Test
  fun plus() {
    Point(1, 2) + Point(2, 3) shouldBe Point(3, 5)
  }

  @Test
  fun plusWithNegativeValues() {
    Point(4, 8) + Point(-2, -3) shouldBe Point(2, 5)
  }

  @Test
  fun manhattanDistanceZero() {
    Point(2, 4).manhattanDistanceTo(Point(2, 4)) shouldBe 0
  }

  @Test
  fun manhattanDistanceNonZero() {
    Point(2, 4).manhattanDistanceTo(Point(-2, 8)) shouldBe 8
    Point(0, 0).manhattanDistanceTo(Point(0, 1)) shouldBe 1
    Point(0, 0).manhattanDistanceTo(Point(1, 1)) shouldBe 2
  }
}
