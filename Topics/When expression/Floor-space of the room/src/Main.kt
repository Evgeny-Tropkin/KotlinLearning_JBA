import kotlin.math.roundToInt
import kotlin.math.sqrt

fun main() {
    // write your code here
    val shape = readln()
    var area = 0.0
    when (shape) {
        "triangle" -> {
            val edges = getLengths(3)
            val p = (edges.sum() / 2.0)
            area = sqrt(p * (p - edges[0]) * (p - edges[1]) * (p - edges[2]))
        }
        "rectangle" -> {
            val edges = getLengths(2)
            area = edges[0] * edges[1]
        }
        "circle" -> {
            val radius = getLengths(1)[0]
            area = 3.14 * radius * radius
        }
        else -> {println("No such shape of floor")}
    }
    println(area)
}
fun getLengths(numberOfEdges: Int): MutableList<Double> {
    return MutableList(numberOfEdges) { readln().toDouble()}

}