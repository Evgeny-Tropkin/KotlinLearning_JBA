// write your code here
fun getLastDigit(nuber: Int):String {
    val str = nuber.toString()
    val res = str[str.length-1].toString()
    return res
}
/* Do not change code below */
fun main() {
    val a = readLine()!!.toInt()
    println(getLastDigit(a))
}
