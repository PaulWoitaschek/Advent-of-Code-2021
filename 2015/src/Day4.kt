package aoc.year2015

import aoc.library.Puzzle
import java.security.MessageDigest

object Day4 : Puzzle<Int, Int>(4) {

  override fun solvePart1(input: String): Int = lowestMatching(input, leadingZeroes = 5)
  override fun solvePart2(input: String): Int = lowestMatching(input, leadingZeroes = 6)

  private fun lowestMatching(
    input: String,
    leadingZeroes: Int,
  ): Int {
    val md5 = MessageDigest.getInstance("MD5")!!
    var value = 0
    while (true) {
      val candidate = "${input}$value"
      val hash = md5.digest(candidate.toByteArray())
      if (hash.hasLeadingZeroes(leadingZeroes)) {
        return value
      }
      value++
    }
  }

  private fun ByteArray.hasLeadingZeroes(count: Int): Boolean {
    var leading = 0
    var index = 0
    while (true) {
      when (get(index++)) {
        0.toByte() -> {
          leading += 2
          if (leading >= count) return true
        }
        in 0..15 -> {
          leading++
          return leading >= count
        }
        else -> return false
      }
    }
  }
}
