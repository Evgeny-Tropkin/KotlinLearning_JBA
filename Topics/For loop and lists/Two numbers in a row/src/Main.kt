fun main() {
    val size = readln().toInt()
    val list = MutableList(size) { readln().toInt() }
    val (x, y) = readln().split(' ').map { it.toInt() }
    var result = "YES"

    for (pos in 0 until list.lastIndex) {
        if ((list[pos] == x && list[pos + 1] == y) ||
            (list[pos] == y && list[pos + 1] == x)
        ) {
            result = "NO"
            break
        }
    }
    println(result)
}
