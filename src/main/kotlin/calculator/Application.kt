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
    
    val tokens = input.split(Regex("[,:]")).map { it.trim() }
    
    return tokens.mapNotNull { token ->
        val digits = token.filter (Char::isDigit)
        digits.takeIf {it.isNotEmpty()}?.toInt()
    }
}