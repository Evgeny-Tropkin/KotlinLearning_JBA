fun main() {
    val n = readln().toInt()
    var res = 0
    repeat(n) {
        val element = readln().toInt()
        if (element % 4 == 0) {
            if (element > res) res = element
        }
    }
    print(res)
}