fun main() {
    // write your code here
    val size = readln().toInt()
    val inp = MutableList(size) { readln().toInt() }
    print(inp.lastOrNull())
    for (pos in 0 until size - 1) {
        print(" " + inp[pos])
    }
}