import kotlin.math.abs

fun main() {
    val firstKnightCoords = readln().split(' ')
    val secondKnightCoords = readln().split(' ')

    val deltaX1 = abs(firstKnightCoords[0].toInt() - secondKnightCoords[0].toInt()) == 1
    val deltaY1 = abs(firstKnightCoords[1].toInt() - secondKnightCoords[1].toInt()) == 2
    val deltaX2 = abs(firstKnightCoords[0].toInt() - secondKnightCoords[0].toInt()) == 2
    val deltaY2 = abs(firstKnightCoords[1].toInt() - secondKnightCoords[1].toInt()) == 1

    if (deltaX1 && deltaY1 || deltaX2 && deltaY2) {
        println("YES")
    } else println("NO")
}