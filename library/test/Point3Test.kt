package aoc.library

import aoc.library.Point3.Axis
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Point3Test {

  @Test
  fun `plus should correctly add two Point3 objects`() {
    val p1 = Point3(1, 2, 3)
    val p2 = Point3(4, 5, 6)
    val result = p1 + p2
    result shouldBe Point3(5, 7, 9)
  }

  @Test
  fun `minus should correctly subtract two Point3 objects`() {
    val p1 = Point3(10, 20, 30)
    val p2 = Point3(1, 2, 3)
    val result = p1 - p2
    result shouldBe Point3(9, 18, 27)
  }

  @Test
  fun `get should return the correct coordinate value`() {
    val p = Point3(7, 8, 9)
    p[Axis.X] shouldBe 7
    p[Axis.Y] shouldBe 8
    p[Axis.Z] shouldBe 9
  }

  @Test
  fun `sign should return a Point3 with each axis signum`() {
    val p1 = Point3(-10, 20, -30)
    p1.sign() shouldBe Point3(-1, 1, -1)

    val p2 = Point3(0, 0, 0)
    p2.sign() shouldBe Point3(0, 0, 0)
  }

  @Test
  fun `Zero should be a Point3 with all coordinates as zero`() {
    Point3.Zero shouldBe Point3(0, 0, 0)
  }
}
