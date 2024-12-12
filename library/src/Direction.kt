package aoc.library

enum class Direction(var char: Char) {
  Left('L'),
  Up('U'),
  Right('R'),
  Down('D'),
  ;

  fun clockwise(): Direction = entries[(ordinal + 1) % entries.size]

  fun counterClockwise(): Direction = entries[(ordinal + entries.size - 1) % entries.size]

  fun opposite(): Direction = when (this) {
    Left -> Right
    Right -> Left
    Up -> Down
    Down -> Up
  }

  companion object {
    private val byChar = entries.associateBy { it.char }
    fun parse(input: Char): Direction = byChar[input] ?: throw IllegalArgumentException("Invalid char=$input")

    fun fromArrowOrNull(arrow: Char): Direction? = when (arrow) {
      '<' -> Left
      '>' -> Right
      '^' -> Up
      'v' -> Down
      else -> null
    }
  }
}
