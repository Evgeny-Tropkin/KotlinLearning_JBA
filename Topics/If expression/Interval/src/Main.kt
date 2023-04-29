fun main() {
    // write your code here
    val num = readln().toInt()
    val res = if (num in -14 .. 12 || num in 15 until 17 || num >= 19) "True" else "False"
    print(res)
}
