fun main() {
    var numberOfPositive = 0
    repeat(3) { if (readln().toInt() > 0) numberOfPositive++ }
    println(numberOfPositive == 1)
}