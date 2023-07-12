fun perimeter(x1: Double, y1: Double, x2: Double, y2: Double, x3: Double, y3: Double, x4: Double = x1, y4: Double = y1): Double {
    data class Point(val x: Double, val y: Double)

    val points = listOf(Point(x1, y1), Point(x2, y2), Point(x3, y3), Point(x4, y4), Point(x1, y1))
    var p = 0.0

    for (n in 0 until points.size - 1) {
        val dx = points[n].x - points[n + 1].x
        val dy = points[n].y - points[n + 1].y
        p += Math.hypot(dx, dy)
    }
    return p
}
