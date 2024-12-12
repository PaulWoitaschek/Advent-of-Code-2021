package aoc.library

fun IntRange.intersects(other: IntRange): Boolean = last >= other.first && first <= other.last
