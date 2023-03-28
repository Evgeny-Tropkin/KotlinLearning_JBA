fun main() {
    val firstInt = readln().toInt()
    val secondInt = readln().toInt()
    var sum = 0
    for (num in firstInt..secondInt) {
        sum += num
    }
    println(sum)
}
