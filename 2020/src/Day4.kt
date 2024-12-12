package aoc.year2020

import aoc.library.Puzzle

private typealias Passport = Map<String, String>

object Day4 : Puzzle<Int, Int>(4) {

  override fun solvePart1(input: String): Int = input.split("\n\n").filter { it.isNotEmpty() }
    .map {
      it.split(" ", "\n").filter { it.isNotEmpty() }
        .associate {
          val (field, value) = it.split(":")
          field to value
        }
    }
    .count {
      it.allFieldsPresent()
    }

  override fun solvePart2(input: String): Int = input.split("\n\n").filter { it.isNotEmpty() }
    .map { parsePassport(it) }
    .count {
      it.allFieldsCorrect()
    }

  fun parsePassport(passport: String): Passport = passport.split(" ", "\n").filter { it.isNotEmpty() }
    .associate {
      val (field, value) = it.split(":")
      field to value
    }

  private fun Passport.hasValidBirthYear(): Boolean {
    val birthYearValue = get("byr") ?: return false
    if (birthYearValue.length != 4) return false
    val birthYear = birthYearValue.toIntOrNull() ?: return false
    return birthYear in 1920..2002
  }

  private fun Passport.hasValidIssueYear(): Boolean {
    val issueYearValue = get("iyr") ?: return false
    if (issueYearValue.length != 4) return false
    val issueYear = issueYearValue.toIntOrNull() ?: return false
    return issueYear in 2010..2020
  }

  private fun Passport.hasValidExpatriationYear(): Boolean {
    val expirationYear = get("eyr")?.toIntOrNull() ?: return false
    return expirationYear in 2020..2030
  }

  private fun Passport.hasValidHeight(): Boolean {
    val height = get("hgt") ?: return false
    return if (height.endsWith("cm")) {
      val cm = height.substringBefore("cm").toIntOrNull() ?: return false
      cm in 150..193
    } else if (height.endsWith("in")) {
      val inch = height.substringBefore("in").toIntOrNull() ?: return false
      inch in 59..76
    } else {
      false
    }
  }

  private fun Passport.hasValidHairColor(): Boolean {
    val hairColor = get("hcl") ?: return false
    if (hairColor.firstOrNull() != '#') return false
    val hairColorValue = hairColor.drop(1)
    if (hairColorValue.length != 6) return false
    return hairColorValue.all {
      it in '0'..'9' || it in 'a'..'f'
    }
  }

  private fun Passport.hasValidEyeColor(): Boolean = get("ecl") in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

  private fun Passport.hasValidPassportId(): Boolean {
    val passportId = get("pid") ?: return false
    return passportId.length == 9 &&
      passportId.all {
        it.digitToIntOrNull() != null
      }
  }

  private fun Passport.allFieldsPresent(): Boolean {
    val keysWithoutCountryId = keys.toSet() - "cid"
    return keysWithoutCountryId.size == 7
  }

  fun Passport.allFieldsCorrect(): Boolean = hasValidHeight() &&
    hasValidBirthYear() &&
    hasValidExpatriationYear() &&
    hasValidIssueYear() &&
    hasValidHairColor() &&
    hasValidPassportId() &&
    hasValidEyeColor()
}
