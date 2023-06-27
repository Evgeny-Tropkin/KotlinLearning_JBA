fun main() {
    // write your code here
    val year = readln().toInt()
    var isLeap = year % 4 == 0
    if (isLeap && year % 100 == 0) {
        isLeap = year % 400 == 0
    }
    print(if (isLeap) "Leap" else "Regular")
}