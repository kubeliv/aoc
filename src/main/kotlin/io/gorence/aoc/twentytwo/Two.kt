package io.gorence.aoc.twentytwo

const val OtherRock = 'A'
const val OtherPaper = 'B'
const val OtherScissors = 'C'
const val YouRock = 'X'
const val YouPaper = 'Y'
const val YouScissors = 'Z'
const val YouLose = 'X'
const val YouDraw = 'Y'
const val YouWin = 'Z'

// Convert "You" into "Other" for comparison.
const val MagicConstant = 23

fun main() {
  val totalScore = simulateTotalScore(INPUT)
  println("Total score: $totalScore")
  val totalCorrectScore = simulateTotalScoreCorrect(INPUT)
  println("Total correct score: $totalCorrectScore")
}

fun simulateTotalScore(input: String): Int {
  return input.split("\n").sumOf { line ->
    val other = line[0]
    val you = line[2]
    score(other, you)
  }
}

fun score(other: Char, you: Char): Int {
  var score = when (you) {
    YouRock -> 1
    YouPaper -> 2
    YouScissors -> 3
    else -> throw Exception("Unknown type")
  }

  if (other == you - MagicConstant) {
    score += 3
  } else if ((you == YouRock && other == OtherScissors) ||
    (you == YouPaper && other == OtherRock) ||
    (you == YouScissors && other == OtherPaper)
  ) {
    score += 6
  }

  return score
}

fun simulateTotalScoreCorrect(input: String): Int {
  return input.split("\n").sumOf { line ->
    val other = line[0]
    val you = line[2]
    val f = scoreCorrect(other, you)
    f
  }
}

fun scoreCorrect(other: Char, you: Char): Int {
  var score = when (you) {
    YouLose -> 0
    YouDraw -> 3
    YouWin -> 6
    else -> throw Exception("Unknown type")
  }

  val type = if (other == OtherRock && you == YouLose) {
    YouScissors
  } else if (other == OtherRock && you == YouWin) {
    YouPaper
  } else if (other == OtherPaper && you == YouLose) {
    YouRock
  } else if (other == OtherPaper && you == YouWin) {
    YouScissors
  } else if (other == OtherScissors && you == YouLose) {
    YouPaper
  } else if (other == OtherScissors && you == YouWin) {
    YouRock
  } else {
    other + MagicConstant
  }
  score += when (type) {
    YouRock -> 1
    YouPaper -> 2
    YouScissors -> 3
    else -> throw Exception("Bad move type")
  }

  return score
}

private const val TESTINPUT = """A Y
B X
C Z"""

private const val INPUT = """A Z
A Y
B X
B X
C X
B X
A X
A X
C X
A X
A X
A Y
B X
A Y
C X
C X
A X
A Y
C X
B X
A X
B X
A X
A X
B Y
B Y
A X
A X
A X
A X
A X
A Y
A X
B X
B Y
A Y
C Z
C X
A X
A Y
A X
A X
A Y
A X
A Y
A X
B X
C X
A X
B X
A X
C X
A Y
B X
B X
A X
A X
C Y
B X
A X
C X
B X
B X
A X
A X
B X
A Y
C X
A Y
A X
A X
A Y
B X
A X
A X
A X
B Y
A X
A X
A Y
A X
A X
B Y
B Y
B X
C X
B Y
A X
C X
C X
A Y
A X
A Z
B X
C X
A Y
B X
A X
A X
A X
A Y
C X
A X
A Z
A X
A Y
B Y
A X
C X
B X
A Y
B Z
B X
C Y
B X
B Z
A Y
B X
C X
C Y
A X
A Y
A X
B X
A Y
B Y
A X
C Y
A Y
B Y
A X
B Y
B X
B Y
A X
B Y
A X
C Z
A X
C X
A Y
A X
C X
A X
A X
A X
C X
B Z
A X
B Y
B X
A Y
A X
A X
B Y
B Y
A Y
B X
A X
C Y
A X
C X
A X
A X
A X
A X
A X
C Z
C X
A Z
C Z
A X
A Y
B X
C X
B X
B Y
C X
B X
A X
A X
A X
A Y
B X
B Y
A X
C X
A X
A X
A X
A X
C Y
A Z
C X
A X
A Y
B X
A Y
A X
A Y
A X
A X
A X
A X
A Y
A X
B X
A X
A X
C Y
C Y
A X
C X
A X
A X
C X
C X
B Z
B X
B X
A X
A Y
B Y
A X
A X
C X
C X
C X
B X
C X
B Y
A X
B Y
B X
B X
A X
B Y
A Y
C X
B X
C X
A Y
B X
C X
A X
C X
A X
B Y
A X
A X
B Z
C X
B X
C X
B X
A X
A X
A X
A X
A X
C X
C X
A X
A Z
A Y
A X
A X
A X
A X
C Y
C Y
B Z
A X
A X
B X
B X
A Y
A X
B Y
A X
B Y
A X
C Y
C X
A Y
B Y
C X
A X
A X
A X
A Y
A X
A X
A X
B Y
B X
C X
C Y
A Y
A Z
B X
C X
B X
C X
A X
B Z
B Y
A X
C X
A Y
B X
B X
A Y
A X
A X
A Y
A X
B X
A X
A X
C Y
C Y
A Y
A Y
B Y
B Y
B Y
B X
A X
A Y
B Y
B X
A Y
A X
A Y
C X
C X
B Y
A Y
B Y
A Y
A X
B Y
B X
C X
A X
A X
A X
B Y
B X
A X
B Y
A Y
A X
C X
A X
B Y
A X
A X
B Y
A Y
A X
C Y
B Z
A Y
C X
A Y
A X
B Y
A X
B Y
B Y
B X
B X
A X
A X
C X
B Y
B X
C X
B Y
A X
A X
A X
A X
B Y
C X
B X
A X
A X
A Y
C Y
A Y
B X
A X
B X
A X
A Y
B Y
A X
C X
C X
A Y
B X
C Y
A Y
B X
A X
A Y
C X
A X
A X
B Y
A Y
B X
B Y
B Y
A X
A X
A X
B Y
A Y
A X
B X
A Y
A Y
B X
C Y
A X
A Y
A X
B X
A X
B X
C Z
A X
A X
B Y
A X
A X
A X
C Z
C X
A Z
C Y
B X
A Y
B X
A X
A Y
A X
C X
A X
A X
C Y
A Z
C Y
B Y
B X
B X
A Y
C X
B X
A Z
A X
A Z
C X
A X
C Z
C X
B Z
C Y
B Y
A X
B Y
A X
A X
C X
B X
A X
C Z
B Y
B Y
A Y
B Y
A X
A X
B Y
A X
A X
C Y
C X
A X
C Y
C X
B X
B X
A X
B Y
A Y
A X
B Y
B Y
B X
B X
A X
B X
B X
B Y
B Y
C X
B X
B X
A X
A X
A X
A X
A Y
C Y
A X
A Y
C Z
A Y
C X
A X
C Y
A X
A X
A Y
A Z
A Y
A Y
A X
A Y
A Z
C X
C Z
B X
A X
A X
B X
A X
A X
A X
C X
A X
A Y
A X
A X
A Y
C Z
C X
A X
A X
A Z
A X
C X
A Y
C X
A Y
C X
C X
A X
A X
A Y
B Y
C X
C X
B X
B Z
B X
B X
A X
C X
A Y
C X
A Z
B X
B Y
A Y
A Y
A X
A Y
A X
A X
A Y
A X
A X
B Z
A X
A X
A X
B Y
B Y
C Z
A Y
A Y
B Y
A X
B X
C X
C Y
C X
B X
C X
A Y
A X
A X
B X
C X
B X
A Y
B X
B Z
A Y
A X
A Y
B X
B Z
A X
A X
C X
A X
A Y
A X
B X
A X
A X
A Z
B X
B Y
C Y
C X
A Y
A X
B Y
C X
A X
C Y
A X
A Y
A Y
A X
A X
C X
C X
A Y
A Y
C X
A X
B X
C X
B Y
C Y
C X
B X
B Y
C X
B X
B Y
A X
A X
B X
A Y
A X
B X
A X
C Y
A X
B X
A Y
C Y
A X
A Y
A Z
C Z
A X
A X
C X
A X
B X
A X
C X
C X
A Y
A X
C Y
A Y
A X
B Y
A X
A Y
A X
C Z
C Z
C X
A X
A X
A X
A X
C X
A X
C X
B X
B X
A X
A Y
B Y
B X
B X
A Y
A Y
A X
A X
B Y
B X
A X
C X
B X
C X
B Y
B Z
B Y
A X
A X
A X
A X
A X
B Y
A X
C X
B Z
A X
C Y
A X
B X
C Y
A X
A Y
B X
A X
B Y
B X
A Y
C Y
C X
A Y
A X
A X
A X
C X
B X
C X
A Y
A X
A Y
C Z
C X
A X
A Y
B X
B X
B X
B X
A X
B X
A Z
B Y
A X
A X
A X
C Z
A Y
A Y
C X
C X
C Y
B Y
A Y
A X
A X
C Y
A X
C X
A X
A X
C Y
A Y
A X
C X
A X
A Y
C X
A Y
A X
A X
A X
B X
B X
A X
B Y
C Y
C X
A Y
B X
B X
B Z
C X
B Y
A Y
C Z
B X
C X
A X
A Y
B Y
B X
C Z
B X
A X
C X
C X
A X
A X
A Y
B Y
B X
A Y
C Y
B Y
B Y
A X
A Y
A X
B X
A Y
A X
A Y
A X
B Z
B X
A X
A Y
A X
B Y
A Y
A X
A X
C X
A X
A X
A X
A X
B Y
B X
A X
A X
C Y
C X
A Y
A X
B X
A X
B X
B X
B Y
A X
B X
B Y
A X
B X
C X
A X
A X
C Z
A X
B X
A X
B X
B X
B Y
A X
B Y
C Y
A X
C X
A X
B X
B Y
A X
C X
B X
A X
C Y
C X
B X
A X
B X
A Z
C Y
A X
A X
B X
A Y
B X
A X
A Y
B X
A X
C Y
A X
C X
B X
A X
C Y
C X
A X
B Y
B X
A Y
B X
A X
B X
A Y
B X
A X
A X
C X
A X
A X
A X
A X
C X
C X
B X
B Y
B X
B Z
A X
B X
B Y
B X
B X
A X
A Z
A Z
A X
B Y
A X
B X
B Y
A X
B Y
B X
A X
C X
C Y
A X
A X
B X
B X
B Y
A X
C X
B Y
A X
A Z
A X
C Y
A Y
B X
C X
C X
A X
B X
C X
A Z
A X
A X
B Y
C X
A X
A Y
C Z
B X
A Y
A X
A Z
C Y
A X
C X
A X
C Y
B X
A X
A X
B Y
A X
B X
B X
B X
C X
C X
A X
C Z
A X
C Y
A X
C Y
A Y
B Y
B X
B Y
B X
C Y
B Y
B Y
B X
B Y
A Y
A Y
C Z
A X
A X
A X
A Z
B X
B Y
B X
B Y
A X
C X
B X
A X
C Y
B X
C X
C Y
B Y
A X
B Y
B X
C X
B X
C X
B X
A X
C X
B Z
B Z
C X
A Y
B X
A Y
A X
B X
C Y
C X
A Y
C Y
B Z
B Y
B Y
B Y
B Z
B Y
A X
A X
A Y
B Y
B Y
A X
A Z
C X
A X
A Y
C X
A Y
A X
B X
A X
A X
C X
A X
A X
C Y
B Y
A Y
A X
A X
B Y
A X
B X
A X
A X
B X
B X
A X
B Y
A X
A Y
B Y
A Y
A X
C X
A Y
A X
C X
A X
A X
B X
C Y
C Y
B X
B Z
A X
A Y
A X
A X
C Z
A X
B X
A Z
A X
A X
A X
A X
A X
A X
C X
B X
B X
C X
A X
A X
A X
A X
A X
A X
B X
A X
C X
A Y
A X
A X
A X
A X
B X
B Y
A Y
A X
A X
C X
C X
A X
A X
C X
A X
A X
A X
A X
A X
A X
C X
A X
A X
A X
A X
B X
A X
A X
B Y
C Y
A X
A Y
C Y
A X
B X
B X
A X
A X
A X
B X
A X
A X
A X
A Y
A X
B Y
A Y
A X
A Y
A X
C X
A X
C Y
B X
B X
A X
A X
A X
A X
A X
C Y
A Z
A X
B X
B X
A X
A X
C Z
A X
B Z
B Y
C Y
A X
A X
C X
B X
B X
B X
C X
A X
A Y
B Y
C X
A X
B Y
A X
C Z
B X
A Y
A X
A X
C Y
A X
A X
C X
A X
B X
B Y
B Y
B X
A X
A Y
C X
A X
A X
A X
B X
C X
B Y
A X
A X
A Y
A X
A X
B X
C X
C X
A Y
B X
A X
A X
A X
B Y
A X
B X
A X
A X
A X
A Y
A X
A Z
C X
B Y
B X
A X
B X
C X
A X
A X
B X
A X
A X
A X
A X
B X
A X
C X
A Y
B X
C X
A X
B X
A X
B Y
A X
C Y
A X
C Y
C X
B Y
A Y
A X
A X
B X
A X
C X
A X
A Z
B X
A X
A X
A Y
A X
A X
C X
B Y
C Z
A X
A Y
B X
B X
A X
B Y
C X
A X
B X
A Y
B X
A X
A Y
A X
A X
C Y
B X
A Y
C X
C X
A Y
A Y
A X
B Z
A Y
C X
C X
C Y
A Y
B X
B Z
A X
B Y
A X
A X
B X
A Y
C X
C Y
A X
B Y
A Y
A X
B Z
B Y
A X
B X
A X
A X
B Y
A Y
A X
A X
A X
A Y
A X
A X
C X
A X
A X
C X
A X
A X
C Z
B X
A Y
A X
B Y
B X
A X
A X
A X
C Y
C Y
B Y
A X
C X
C X
B X
A X
A X
A X
A Y
A X
A X
B Z
C X
A X
A X
A X
B Y
B X
C X
B X
A X
B X
A X
C X
A X
C X
A X
C X
B Y
B X
A X
A X
B Y
B X
B X
A X
B X
C Y
A X
A Y
A Y
A Y
A Z
B Y
A X
A X
A X
A X
A Z
A X
A X
A X
B Z
B Y
C Y
A X
A X
B X
C X
A Y
A X
B X
A X
A X
B X
B Y
A X
B X
C X
B X
A X
A X
A X
A X
B X
B X
C Y
B X
B X
A X
B X
A X
B Y
B X
A X
A X
A Y
B X
A X
B Y
A Y
B Y
B X
A Z
A X
B Y
A X
B X
A X
A X
A X
B Y
B X
A X
C Z
A Y
A Y
C X
B Z
B X
C Z
B X
B X
A X
A X
B Y
C X
A X
A Z
B X
A X
A X
B Y
A X
A X
A X
B Y
A X
C Y
A X
C Y
A X
C Y
B X
A Y
A X
B X
A X
B Y
A X
B X
B X
A Z
A X
C Y
B Y
A X
A X
C X
B Y
C Y
C X
A Y
B Y
A Y
A Y
B X
A X
A X
A X
A X
A Y
A X
A X
A X
B Y
A X
A Y
C X
A X
B X
A X
A X
A X
C Z
A X
A Y
A X
C Z
B Y
B X
A X
A Z
A X
A X
A Y
A X
A X
A X
B Y
A X
B X
A X
A X
A X
A X
B X
B Z
C X
C X
C X
B Y
A X
B Y
B X
A Y
B X
B X
A X
C Y
B X
A X
A X
A X
A X
A X
A X
B Y
C Y
B X
C Y
B Y
C X
A Y
C X
B X
C X
B X
A X
A X
B Y
A X
C X
A Y
C X
C X
B Y
A X
C X
C X
B Z
A X
C X
A Y
B Y
A X
A Y
A X
B X
A X
A X
B Y
A Z
A X
B Y
B X
A X
A Y
B X
B X
A X
A X
A Y
A X
C X
A X
B X
C X
C X
C Y
A X
B X
B Y
A X
C X
A X
B Y
A X
B X
B Z
A X
A X
A X
B X
C X
C X
B X
A Y
A X
A Y
A X
A X
A X
A X
C Y
B X
A X
A X
A X
B Y
A X
B X
B Y
A Z
A Y
A X
A Y
A X
A Y
B Z
A X
B Y
B Z
B X
A X
C X
C Y
A X
A X
A X
B X
A Y
C X
A X
B Y
A Y
B X
B X
A X
A Y
B X
A Y
C X
A X
C X
A X
C X
A X
A X
C X
A X
A X
A Y
B X
C X
B Y
A Y
A X
A X
A X
A Z
B Y
A X
A X
B Y
A Y
B Y
A X
B Y
B X
A X
A X
A X
B X
B Y
A X
C Z
B Z
A X
A X
A X
B X
B Y
A X
B Z
A X
A Y
A Y
A Y
C X
B Y
A X
A X
C Y
B X
A Y
A X
A X
B Y
C X
C X
C X
A X
A X
A X
B Z
A X
C X
A X
A X
B X
B X
A X
C Y
A X
B X
C X
B Y
A X
B Y
C X
A X
C X
B Y
A X
A Y
C X
C Z
A X
C X
A X
A X
C X
A Y
C X
B Y
A X
C Z
A X
B X
B Y
A Y
A X
A X
A X
A X
A Y
A Y
B X
C X
A Y
A X
C X
B X
B Y
C X
B X
A X
B X
A X
C Y
A X
A X
A X
A X
C X
A X
B X
A X
B X
A X
A Y
C X
C Y
C X
A Z
B X
A Z
A X
B Y
A Y
B X
A X
B Y
A Y
A X
B Y
A X
B X
A X
A X
A X
A X
B Y
A Y
A X
A X
B X
B Z
A Y
A Y
C Y
C Y
A X
C X
A X
B Y
B X
B Y
B X
C X
B X
C X
A X
B Y
A X
B X
A X
B X
C Y
B X
A X
A Y
A X
B Y
C X
C X
A X
B Z
A X
A X
B Y
A X
A X
B X
A Y
C X
A X
C Y
A Y
C X
A Y
A X
A X
A X
A Y
B X
B X
A Y
C X
B Y
A Y
B X
B X
B X
B X
A X
B X
B X
A X
C X
A X
C X
C X
A X
C X
B X
A Y
A Z
A X
A X
A X
A Y
A X
A X
C Z
A X
B X
C X
A X
B X
C X
C X
A X
B Y
A X
A X
A X
A Y
B Y
B Y
A X
B X
A X
C X
A X
B Y
C Y
C X
C X
B X
B X
C X
A Y
B X
A X
A X
A X
A X
A Y
B X
B X
C Y
C Y
B Y
B X
A X
C Y
A X
C Z
C X
A X
B Z
A X
A Z
C X
B Y
A X
A Y
A X
B X
A Y
C X
C Y
B X
B X
B Y
B Y
C X
B Y
B Y
B X
C X
C X
A X
A X
A Y
A X
A Y
B X
A X
B Y
B X
A X
A X
A Y
B X
B X
A X
B X
C Y
A Y
B Y
C Y
B Y
A X
B Y
B X
B Z
A Y
A Y
C Y
B X
A X
A X
B Y
A X
A X
B X
A X
A X
A X
B X
B X
B Y
A X
C X
A X
B X
A X
A X
A Y
B X
B X
B X
C X
B Y
C Z
A X
C X
A X
B X
A X
A Y
B Y
A Z
A X
C X
A Y
A X
A X
A X
A X
A Y
A X
C X
A X
C Y
B X
A X
A X
A X
A X
A X
C X
A X
B X
B Y
B Y
B X
C Z
C X
B X
C Y
A X
A X
A X
A X
A X
C X
A X
B X
C X
C X
A X
A X
B X
A X
A X
A X
B X
A X
C X
A X
A Y
A X
B Y
A X
B X
A Y
C X
B X
C Y
C Y
B X
A X
B X
C X
A X
C X
A X
C X
B X
B X
C X
C X
B X
B X
C Y
B X
A X
B X
B X
B Y
A Y
B X
A X
C Y
A Y
C Y
A X
B Y
A X
A X
A X
B Y
B X
A Y
A X
A X
A Y
B X
B Y
A Y
B X
C X
C Z
B X
A Y
A X
C X
A X
C X
B Z
C X
A Y
A X
C X
B Y
A Z
B Y
A X
A X
A Y
C X
A Z
A X
B Y
A X
B X
A Y
A Y
A X
A X
A X
C Y
B X
A X
B X
B Y
B Y
A X
A X
A Y
A X
C X
B X
C X
B X
B X
C Y
A Y
B X
A X
A X
A X
C X
C X
A X
B X
A X
A X
A Z
A X
C X
B X
A X
C Y
A Y
C Y
B Y
B Y
B Z
A X
C X
A X
B Y
B Y
A X
B Y
A X
B Y
B X
B X
C X
B Y
B X
A X
A Y
B X
A X
B Z
A Y
B X
B X
A Y
C Y
B X
B Y
A X
B X
A Y
A Y
B X
B Y
B X
A X
C X
A X
A X
A X
B Y
A Y
B X
C X
C Z
A X
B Y
A Y
A X
B X
A X
A Y
A X
A Y
A X
A X
A X
C X
A Y
C Y
A X
A X
C Z
B X
A Y
B X
B X
B X
B X
B X
A X
A X
B X
B Y
A X
C Y
C X
A X
A X
A X
A X
A X
A X
A X
A Y
B Y
A X
A X
A Z
C Y
C Y
B X
C Z
A X
A X
A X
B Z
B Y
A X
A X
A X
B X
A X
C X
C X
C X
B X
A X
A X
C Z
B Z
C Y
A X
A X
A X
C Z
A X
A X
C X
C X
A X
C Y
A X
A Y
A X
A X
A Z
C X
C X
A Y
B X
B X
A Y
A X
B X
C Y
A X
A Y
B Z
A X
C Y
A X
B Y
A X
A Y
A X
C Z
A X
B X
A X
B Y
A X
A X
A X
A X
A X
B Y
C X
A X
B X
A X
A Y
A Z
A X
A X
B Y
B X
C X
A X
A X
B X
B Y
B Y
A Y
B Z
B Y
A X
A X
A X
A X
B X
C Y
A X
A X
A X
A X
A X
A X
A X
A X
B Y
A X
B X
C X
C X
C X
C X
A X
B Y
A X
B X
B X
A X
A X
C Z
A X
B X
B X
C X
A X
B X
B X
B X
B X
B Y
A X
B X
A Y
A X
A Y
B Y
A X
C Y
B X
A X
A X
A X
A Z
A Y
A X
C X
C X
B X
A X
A X
B X
A X
A Y
C Z
A Y
B X
B Y
A X
C Y
B Z"""
