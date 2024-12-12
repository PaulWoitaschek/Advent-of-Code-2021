package aoc.library

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class RangeIntersectTest {

  @Test
  fun nonOverlappingRangesShouldNotIntersect() {
    (0..5).intersects(6..10) shouldBe false
    (10..15).intersects(0..9) shouldBe false
  }

  @Test
  fun overlappingRangesShouldIntersect() {
    (0..5).intersects(5..10) shouldBe true
    (3..8).intersects(6..10) shouldBe true
  }

  @Test
  fun nestedRangesShouldIntersect() {
    (0..10).intersects(2..8) shouldBe true
    (2..8).intersects(0..10) shouldBe true
  }

  @Test
  fun sameRangesShouldIntersect() {
    (0..5).intersects(0..5) shouldBe true
  }

  @Test
  fun adjacentRangesShouldNotIntersect() {
    (0..5).intersects(6..6) shouldBe false
  }
}
