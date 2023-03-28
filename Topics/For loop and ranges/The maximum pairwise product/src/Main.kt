fun main() {
    val numCount = readln().toInt()
    val numbers = MutableList(numCount) { readln().toInt() }.sortedDescending()
    if (numCount == 1) { println(numbers[0]) } else println(numbers[0] * numbers[1])
}