import kotlin.math.abs

fun main() {
    // the first knight coordinates
    val (x1, y1) = readln().split(' ').map { it.toInt() }
    val (x2, y2) = readln().split(' ').map { it.toInt() }

    println(if (abs((x1 - x2) * (y1 - y2)) == 2) "YES" else "NO")
}