package calculator
import camp.nextstep.edu.missionutils.Console.readLine

fun main() {
    val input = readInput()
    val numbers = extractNumbers(input)
    val sum = numbers.sum()
    println("결과 : $sum")
}

fun readInput(): String {
    println("덧셈할 문자열을 입력해 주세요.")
    return readLine()
}

fun extractNumbers(input: String): List<Int> {
    if(input.isBlank()) return emptyList()

    val (delimiter, body) = customDelimiter(input)
    val tokens = body.split(delimiter).map { it.trim() }

    if(tokens.any {Regex("-\\d+").containsMatchIn(it)}) {
        throw IllegalArgumentException("잘못된 입력: 음수")
    }

    return tokens.mapNotNull { token ->
        val digits = token.filter (Char::isDigit)
        digits.takeIf {it.isNotEmpty()}?.toInt()
    }
}

fun customDelimiter(input: String): Pair<Regex, String> {
    if (!input.startsWith("//")) return Regex("[,:]") to input
    val lineChangeIndex = input.indexOf("\n")
    if(lineChangeIndex < 2){
        return Regex("[,:]") to input
    }
    val custom = input.substring(2, lineChangeIndex)
    val body = input.substring(lineChangeIndex + 1)
    return Regex("[,:$custom]") to body
}