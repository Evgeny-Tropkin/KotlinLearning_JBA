fun main() {
    val inp = MutableList(2) { readln() }
    for (pos in inp.lastIndex downTo 0) {
        println(inp[pos])
    }
}