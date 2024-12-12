package aoc.library

import kotlin.math.absoluteValue

private fun gcd(
  a: Long,
  b: Long,
): Long = if (b == 0L) a else gcd(b, a % b)

fun lcm(
  a: Long,
  b: Long,
): Long {
  if (a == 0L || b == 0L) return 0
  return (a * b).absoluteValue / gcd(a, b)
}

fun lcm(values: List<Long>): Long {
  var lcm = values.first()
  values.forEach {
    lcm = lcm(lcm, it)
  }
  return lcm
}
