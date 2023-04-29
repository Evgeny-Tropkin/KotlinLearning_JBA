fun main() {
    // write your code here
    val num = readln().toInt()
    val res = if ((num > -15 && num <= 12) || (num > 14 && num < 17) || (num >= 19)) "True" else "False"
    print(res)
}
