package aoc.library

typealias Grid<Tile> = Map<Point, Tile>

fun <Tile> grid(
  input: String,
  tile: (Char) -> Tile,
): Grid<Tile> = buildMap {
  input.lines().forEachIndexed { y, line ->
    line.forEachIndexed { x, char ->
      put(Point(x, y), tile(char))
    }
  }
}
