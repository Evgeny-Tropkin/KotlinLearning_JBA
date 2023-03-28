fun main() {
    val numCount = readln().toInt()
    val numbers = MutableList(numCount) { readln().toInt() }.sortedDescending()
    println(if (numCount == 1) numbers[0] else numbers[0] * numbers[1])
}