fun main() {
    val res = MutableList(3) { 0 }
    repeat(readln().toInt()) {
        res[(readln().toInt() + 3) % 3] += 1
    }
    print(res.joinToString(" "))
}