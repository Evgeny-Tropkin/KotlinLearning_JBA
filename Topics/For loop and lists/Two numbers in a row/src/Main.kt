fun main() {
    val size = readln().toInt()
    val list = MutableList(size) { readln().toInt() }
    val (x, y) = readln().split(' ').map { it.toInt() }

    for (pos in 0..list.lastIndex) {
        if (pos == list.lastIndex){
            println("YES")
            break
        }
        if ((list[pos] == x || list[pos] == y) &&
            (list[pos] * list[pos + 1] == x * y)) {
            println("NO")
            break
        }
    }
}
