fun main() {
    val sizeOfInput = 3
    val numbers = IntArray(sizeOfInput) { readln().toInt() }
    var result = "true"

    repeat(sizeOfInput){
        val currentValue = numbers[it]
        val subArr = numbers.slice(it + 1 until numbers.size)
        for (item in subArr){
            if (item == currentValue) result = "false"
        }
    }
    println(result)
}