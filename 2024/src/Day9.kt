package aoc.year2024

import aoc.library.Puzzle

object Day9 : Puzzle<Long, Long>(day = 9) {

  override fun solvePart1(input: String): Long {
    val blocks = mutableListOf<Int>()
    var id = 0
    input.toList().chunked(2) {
      repeat(it.first().digitToInt()) {
        blocks += id
      }
      id++
      if (it.size > 1) {
        repeat(it[1].digitToInt()) {
          blocks += -1
        }
      }
    }
    while (true) {
      val lastBlockIndex = blocks.lastIndex
      if (blocks[lastBlockIndex] == -1) {
        blocks.removeAt(lastBlockIndex)
      } else {
        val firstSpace = blocks.indexOf(-1)
        if (firstSpace == -1) break
        blocks[firstSpace] = blocks.removeAt(lastBlockIndex)
      }
    }
    return blocks.withIndex().sumOf { (index, element) ->
      if (element == -1) 0L else index * element.toLong()
    }
  }

  override fun solvePart2(input: String): Long {
    val blocks = mutableListOf<Block>()
    var id = -1
    input.toList().chunked(2) {
      id++
      blocks += Block.FileBlock(id, it[0].digitToInt())
      if (it.size > 1) {
        blocks += Block.Space(it[1].digitToInt())
      }
    }
    (id downTo 1).forEach { idToMove ->
      val blockIndex = blocks.indexOfFirst { it is Block.FileBlock && it.id == idToMove }
      val block = blocks[blockIndex] as Block.FileBlock
      val spaceIndex = blocks.indexOfFirst {
        it is Block.Space && it.size >= block.size
      }
      if (spaceIndex != -1 && spaceIndex < blockIndex) {
        val space = blocks[spaceIndex] as Block.Space
        blocks.removeAt(blockIndex)
        blocks.add(spaceIndex, block)
        blocks.add(blockIndex, Block.Space(block.size))
        space.size -= block.size
      }
    }

    var checkSum = 0L
    var index = 0
    blocks.forEach { el ->
      when (el) {
        is Block.FileBlock -> {
          repeat(el.size) {
            checkSum += index * el.id
            index++
          }
        }
        is Block.Space -> {
          index += el.size
        }
      }
    }

    return checkSum
  }

  private sealed interface Block {
    data class Space(var size: Int) : Block
    data class FileBlock(val id: Int, val size: Int) : Block
  }
}
