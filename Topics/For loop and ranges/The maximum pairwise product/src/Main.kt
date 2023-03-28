fun main() {
    val numCount = readln().toInt()
    var maxNum = 1
    var otherNum = 0
    repeat(numCount) {
        val currentNum = readln().toInt()
        if (currentNum > otherNum) {
            if (currentNum > maxNum) {
                otherNum = maxNum
                maxNum = currentNum
            } else {
                otherNum = currentNum
            }
        }
    }
    println(maxNum * otherNum)
}