package aoc.library

import kotlin.math.absoluteValue
import kotlin.math.atan2

data class Point(val x: Int, val y: Int) {

  fun adjacent(): List<Point> = adjacentOrthogonal() + adjacentDiagonal()

  fun adjacentOrthogonal(): List<Point> = listOf(
    Point(x = x, y = y - 1),
    Point(x = x, y = y + 1),
    Point(x = x - 1, y = y),
    Point(x = x + 1, y = y),
  )

  fun adjacentDiagonal(): List<Point> = listOf(
    Point(x = x - 1, y = y - 1),
    Point(x = x - 1, y = y + 1),
    Point(x = x + 1, y = y - 1),
    Point(x = x + 1, y = y + 1),
  )

  fun manhattanDistanceTo(other: Point): Int = (x - other.x).absoluteValue + (y - other.y).absoluteValue

  fun degreesTo(other: Point): Double {
    val atan2 = atan2(
      y = (other.y - this.y).toDouble(),
      x = (other.x - this.x).toDouble(),
    )
    return (Math.toDegrees(atan2) + 90 + 360) % 360
  }

  override fun toString(): String = "($x,$y)"

  operator fun plus(other: Point): Point = Point(x = x + other.x, y = y + other.y)

  operator fun minus(other: Point): Point = Point(x = x - other.x, y = y - other.y)

  companion object {
    val Zero = Point(0, 0)
    fun parse(from: String): Point {
      val (x, y) = from.split(",")
      return Point(x = x.toInt(), y = y.toInt())
    }
  }
}

fun Point.move(
  direction: Direction,
  steps: Int = 1,
): Point {
  if (steps == 0) return this
  return Point(
    x = x + when (direction) {
      Direction.Left -> -steps
      Direction.Right -> steps
      Direction.Up, Direction.Down -> 0
    },
    y = y + when (direction) {
      Direction.Up -> -steps
      Direction.Down -> steps
      Direction.Left, Direction.Right -> 0
    },
  )
}

fun Collection<Point>.printString(): String = associateWith { }.printString {
  if (it in this) "█" else " "
}

inline fun <T> Map<Point, T>.printString(crossinline tile: (Point) -> String): String {
  if (isEmpty()) {
    return "EMPTY MAP"
  }
  val bounds = keys.bounds()
  return (bounds.top..bounds.bottom).joinToString(
    separator = "\n",
    prefix = buildString {
      append('╔')
      append("═".repeat(1 + bounds.right - bounds.left))
      append('╗')
      appendLine()
    },
    postfix = buildString {
      appendLine()
      append('╚')
      append("═".repeat(1 + bounds.right - bounds.left))
      append('╝')
    },
  ) { y ->
    (bounds.left..bounds.right)
      .joinToString(separator = "", prefix = "║", postfix = "║") { x ->
        tile(Point(x, y))
      }
  }
}

fun Collection<Point>.print() {
  println(printString())
}

inline fun <T> Map<Point, T>.print(crossinline tile: (Point) -> String) {
  println(printString(tile))
}

fun Collection<Point>.bounds(): Rect = Rect(
  left = minOf { it.x },
  right = maxOf { it.x },
  top = minOf { it.y },
  bottom = maxOf { it.y },
)
