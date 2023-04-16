fun main() {
    // write your code here
    val lines = MutableList(3) { readln() }
    print(lines[2] == lines[0] + lines[1])
}