fun main() {
    // put your code here
    val a = readln().toInt()
    val b = readln().toInt()
    var res = 1L
    for(num in a until b) {
        res *= num
    }
    println(res)
}