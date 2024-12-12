package aoc.library

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DirectionTest {

  @Test
  fun `clockwise rotation should return the next direction`() {
    Direction.Left.clockwise() shouldBe Direction.Up
    Direction.Up.clockwise() shouldBe Direction.Right
    Direction.Right.clockwise() shouldBe Direction.Down
    Direction.Down.clockwise() shouldBe Direction.Left
  }

  @Test
  fun `counterClockwise rotation should return the previous direction`() {
    Direction.Left.counterClockwise() shouldBe Direction.Down
    Direction.Up.counterClockwise() shouldBe Direction.Left
    Direction.Right.counterClockwise() shouldBe Direction.Up
    Direction.Down.counterClockwise() shouldBe Direction.Right
  }

  @Test
  fun `parse should correctly create Direction from char`() {
    Direction.parse('L') shouldBe Direction.Left
    Direction.parse('U') shouldBe Direction.Up
    Direction.parse('R') shouldBe Direction.Right
    Direction.parse('D') shouldBe Direction.Down
  }

  @Test
  fun `parse should throw an exception for invalid char`() {
    shouldThrow<IllegalArgumentException> {
      Direction.parse('X')
    }
  }
}
