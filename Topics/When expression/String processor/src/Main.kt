fun main() {
    // write your code here
    val inp = MutableList(3) { readln() }

    when (inp[1]) {
        "equals" ->  println(inp[0] == inp[2])

        "plus" -> println(inp[0] + inp[2])

        "endsWith" -> println(inp[0].endsWith(inp[2]))

        else -> println("Unknown operation")
    }
}