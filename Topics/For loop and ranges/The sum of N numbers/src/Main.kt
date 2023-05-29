fun main() {
    // write your code here
    val size = readln().toInt()
    var sum = 0
    for (pos in 1..size) {
        sum += readln().toInt()
    }
    print(sum)
}
