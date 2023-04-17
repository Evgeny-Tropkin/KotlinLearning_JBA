fun main() {
    // put your code here
    val firstChar = readln().first()
    val secondChar = readln().first()

    if (secondChar.code - firstChar.code == 1) {
        val thirdChar = readln().first()
        print(thirdChar.code - secondChar.code == 1)
    }
    else println(false)
}