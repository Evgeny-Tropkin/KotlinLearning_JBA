// write your code here
fun divide(first: Long, second: Long): Double{
    if (second != 0L) {
        val res = first / second
        return res
    }
}
/* Do not change code below */
fun main() {
    val a = readLine()!!.toLong()
    val b = readLine()!!.toLong()
    println(divide(a, b))
}
