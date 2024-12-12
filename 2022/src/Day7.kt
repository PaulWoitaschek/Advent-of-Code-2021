package aoc.year2022

import aoc.library.Puzzle

object Day7 : Puzzle<Long, Long>(7) {

  override fun solvePart1(input: String): Long = directorySizes(input).filter { it <= 100_000 }.sum()

  override fun solvePart2(input: String): Long {
    val directories = directorySizes(input)
    val minSpaceToFree = directories.max() - (70_000_000 - 30_000_000)
    return directories.first { it >= minSpaceToFree }
  }

  private data class PuzzleFile(val size: Long, val path: String)

  private fun directorySizes(input: String): List<Long> {
    val files = parseFiles(input)
    return files.flatMap { file -> file.directories() }.toSet()
      .map { dir ->
        files.filter { file -> file.path.startsWith(dir) }.sumOf { it.size }
      }
      .sorted()
  }

  private fun PuzzleFile.directories(): MutableList<String> {
    val filePath = path
    val directories = mutableListOf<String>()
    filePath.forEachIndexed { index, c ->
      if (c == '/') {
        directories.add(filePath.take(index + 1))
      }
    }
    return directories
  }

  private fun parseFiles(input: String): Set<PuzzleFile> {
    var currentPath = "/"
    val files = mutableSetOf<PuzzleFile>()
    input.lines().filter(String::isNotEmpty).forEach { line ->
      when {
        line.startsWith("\$ cd") -> {
          val path = line.drop(5)
          currentPath = when (path) {
            ".." -> currentPath.dropLast(1).dropLastWhile { it != '/' }
            "/" -> "/"
            else -> "$currentPath$path/"
          }
        }

        line.first().isDigit() -> {
          val (size, name) = line.split(" ")
          files.add(PuzzleFile(size = size.toLong(), path = currentPath + name))
        }
      }
    }
    return files
  }
}
