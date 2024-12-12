package aoc.year2021

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class SnailFishNumberTests {

  @Test
  fun plus() {
    val sum = SnailFishNumber("[[[[4,3],4],4],[7,[[8,4],9]]]") + SnailFishNumber("[1,1]")
    sum shouldBe SnailFishNumber("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]")
  }

  @Test
  fun explode1() {
    testExplode(value = "[[[[[9,8],1],2],3],4]", expected = "[[[[0,9],2],3],4]")
  }

  @Test
  fun explode2() {
    testExplode(value = "[7,[6,[5,[4,[3,2]]]]]", expected = "[7,[6,[5,[7,0]]]]")
  }

  @Test
  fun explode3() {
    testExplode(value = "[[6,[5,[4,[3,2]]]],1]", expected = "[[6,[5,[7,0]]],3]")
  }

  @Test
  fun explode4() {
    testExplode(
      value = "[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]",
      expected = "[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]",
    )
  }

  @Test
  fun explode5() {
    testExplode(
      value = "[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]",
      expected = "[[3,[2,[8,0]]],[9,[5,[7,0]]]]",
    )
  }

  @Test
  fun explode6() {
    testExplode("[[[[0,7],4],[7,[[8,4],9]]],[1,1]]", "[[[[0,7],4],[15,[0,13]]],[1,1]]")
  }

  @Test
  fun split() {
    SnailFishNumber("[[[[0,7],4],[15,[0,13]]],[1,1]]").split()
      .shouldBe(SnailFishNumber("[[[[0,7],4],[[7,8],[0,13]]],[1,1]]"))
  }

  @Test
  fun magnitude1() {
    testMagnitude("[[1,2],[[3,4],5]]", 143)
  }

  @Test
  fun magnitude2() {
    testMagnitude("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]", 3488)
  }

  private fun testMagnitude(
    value: String,
    expected: Int,
  ) {
    SnailFishNumber(value).magnitude() shouldBe expected
  }

  private fun testExplode(
    value: String,
    expected: String,
  ) {
    SnailFishNumber(value).explode() shouldBe SnailFishNumber(expected)
  }
}
