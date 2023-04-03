fun main() {
    val size = readln().toInt()
    val list = MutableList(size) { readln().toInt() }
    val inp = readln()
    var res = "YES"

    for (pos in 1 until list.size) {
        val variant1 = list[pos - 1].toString() + " " + list[pos].toString()
        val variant2 = list[pos].toString() + " " + list[pos - 1].toString()
        if (variant1 == inp || variant2 == inp) {
            res = "NO"
            break
        }
    }
    println(res)
}