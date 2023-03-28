fun main() {
    var min = Int.MAX_VALUE
    val count = readln().toInt()
    repeat(count) {
        val inp = readln().toInt()
        if (inp < min) min = inp
    }
    println(min)
}
