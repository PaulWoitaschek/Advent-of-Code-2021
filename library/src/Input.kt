package aoc.library

import okhttp3.OkHttpClient
import okhttp3.Request
import okio.buffer
import okio.sink
import org.junit.jupiter.api.Assumptions
import java.io.File

fun <Part1, Part2> Puzzle<Part1, Part2>.solvePart1(): Part1 = solvePart1(input())

fun <Part1, Part2> Puzzle<Part1, Part2>.solvePart2(): Part2 = solvePart2(input())

fun Puzzle<*, *>.input(): String = aocInput(day = day)

private fun aocInput(day: Int): String {
  val year = File(System.getProperty("user.dir")).name
  val file = File("../inputs/$year/$day.txt")
    .apply { parentFile.mkdirs() }
  if (!file.exists() || file.length() == 0L) {
    println("Downloading to $file")
    val sessionCookie = System.getenv("AOC_SESSION")
    val cookieExist = !sessionCookie.isNullOrEmpty()
    val message = "AOC_SESSION environment variable must be set to your session cookie"

    Assumptions.assumeTrue(cookieExist, message)

    check(cookieExist) { message }
    val client = OkHttpClient()
    val response = client.newCall(
      Request.Builder()
        .url("https://adventofcode.com/$year/day/$day/input")
        .addHeader(
          "Cookie",
          "session=$sessionCookie",
        )
        .build(),
    ).execute()
    check(response.isSuccessful) {
      "Failed to download input for $year day $day: ${response.code}"
    }
    response.body!!.use {
      it.source().use { source ->
        file.sink().buffer().use { output ->
          output.writeAll(source)
        }
      }
    }
  }
  return file.readText().removeSuffix("\n")
}
