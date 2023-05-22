fun main(args: Array<String>) {
    // write your code here
    val shapeNumber = readln().toInt()
    val shape: String = when (shapeNumber) {
        1 -> "square"
        2 -> "circle"
        3 -> "triangle"
        4 -> "rhombus"
        else -> ""
    }
    if (shape != "") print("You have chosen a $shape")
    else print("There is no such shape!")
}