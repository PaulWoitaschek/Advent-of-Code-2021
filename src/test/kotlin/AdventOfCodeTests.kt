import io.kotest.assertions.assertSoftly
import io.kotest.assertions.withClue
import io.kotest.matchers.shouldBe
import org.junit.Test

class AdventOfCodeTests {

  @Test
  fun day1() {
    Day1.test(part1 = 7, part2 = 5)
  }

  @Test
  fun day2() {
    Day2.test(part1 = 150, part2 = 900)
  }

  @Test
  fun day3() {
    Day3.test(part1 = 198, part2 = 230)
  }

  @Test
  fun day4() {
    Day4.test(part1 = 4512, part2 = 1924)
  }

  @Test
  fun day5() {
    Day5.test(part1 = 5, part2 = 12)
  }

  @Test
  fun day6() {
    Day6.test(part1 = 5934, part2 = 26984457539)
  }

  @Test
  fun day7() {
    Day7.test(part1 = 37, part2 = 168)
  }

  @Test
  fun day8() {
    Day8.test(part1 = 26, part2 = 61229)
  }

  @Test
  fun day9() {
    Day9.test(part1 = 15, part2 = 1134)
    // println(Day9.solvePart1(currentTaskInput()))
    // println(Day9.solvePart2(currentTaskInput()))
  }

  @Test
  fun day8Display() {
    val expected = listOf(
      8394,
      9781,
      1197,
      9361,
      4873,
      8418,
      4548,
      1625,
      8717,
      4315,
    )
    assertSoftly {
      input(8).lines()
        .forEachIndexed { index, line ->
          withClue(line) {
            val outputValue = Day8.Display.parse(line).outputValue()
            outputValue shouldBe expected[index]
          }
        }
    }
  }

  private fun Puzzle.test(part1: Long?, part2: Long?) {
    val input = input(day)
    if (part1 != null) {
      solvePart1(input) shouldBe part1
    }
    if (part2 != null) {
      solvePart2(input) shouldBe part2
    }
  }
}

private fun input(day: Int): String {
  return ClassLoader
    .getSystemResourceAsStream("day$day.txt")!!
    .bufferedReader()
    .readText()
}

@Suppress("unused")
private fun currentTaskInput(): String {
  return ClassLoader
    .getSystemResourceAsStream("task.txt")!!
    .bufferedReader()
    .readText()
}
