// write your function here
fun sum(int1: Int, int2: Int, int3: Int): Int {
    return int1 + int2 + int3
}
fun main() {    
    val a = readLine()!!.toInt()
    val b = readLine()!!.toInt()
    val c = readLine()!!.toInt()

    println(sum(a, b, c))
}
