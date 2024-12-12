package aoc.library

import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RectTest {

  @Test
  fun `initialization with right less than left should throw exception`() {
    assertThrows<IllegalArgumentException> {
      Rect(left = 5, top = 2, right = 4, bottom = 6)
    }
  }

  @Test
  fun `initialization with top greater than bottom should throw exception`() {
    assertThrows<IllegalArgumentException> {
      Rect(left = 1, top = 7, right = 3, bottom = 4)
    }
  }

  @Test
  fun `contains should return true for point inside the rectangle`() {
    val rect = Rect(left = 1, top = 1, right = 5, bottom = 5)
    val pointInside = Point(3, 3)
    (pointInside in rect).shouldBeTrue()
  }

  @Test
  fun `contains should return false for point outside the rectangle`() {
    val rect = Rect(left = 1, top = 1, right = 5, bottom = 5)
    val pointOutside = Point(0, 0)
    (pointOutside in rect).shouldBeFalse()
  }

  @Test
  fun `height should return correct value`() {
    val rect = Rect(left = 1, top = 1, right = 5, bottom = 5)
    rect.height() shouldBe 4
  }

  @Test
  fun `height should be 0 for rectangle with top equal to bottom`() {
    val rect = Rect(left = 1, top = 5, right = 5, bottom = 5)
    rect.height() shouldBe 0
  }
}
