package aoc.year2022

import aoc.library.solvePart1
import aoc.library.solvePart2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day7Test {

  @Test
  fun part1TestInput() {
    Day7.solvePart1(
      """
$ cd /
$ ls
dir a
14848514 b.txt
8504156 c.dat
dir d
$ cd a
$ ls
dir e
29116 f
2557 g
62596 h.lst
$ cd e
$ ls
584 i
$ cd ..
$ cd ..
$ cd d
$ ls
4060174 j
8033020 d.log
5626152 d.ext
7214296 k
      """.trimIndent(),
    ) shouldBe 95437
  }

  @Test
  fun part2TestInput() {
    Day7.solvePart2(
      """
$ cd /
$ ls
dir a
14848514 b.txt
8504156 c.dat
dir d
$ cd a
$ ls
dir e
29116 f
2557 g
62596 h.lst
$ cd e
$ ls
584 i
$ cd ..
$ cd ..
$ cd d
$ ls
4060174 j
8033020 d.log
5626152 d.ext
7214296 k
      """.trimIndent(),
    ) shouldBe 24933642
  }

  @Test
  fun part1() {
    Day7.solvePart1() shouldBe 1477771
  }

  @Test
  fun part2() {
    Day7.solvePart2() shouldBe 3579501
  }
}
