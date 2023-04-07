fun main() {
    val size = readln().toInt()
    val list = MutableList(size) { readln() }.joinToString(" ")
    val inp = readln().split(' ').map { it.toInt() }
    println(
        if (inp.joinToString(" ") in list ||
            inp.reversed().joinToString(" ") in list
        ) "NO" else "YES"
    )
}
