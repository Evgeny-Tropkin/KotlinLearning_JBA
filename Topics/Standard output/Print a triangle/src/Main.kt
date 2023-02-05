const val RIGHT_TRIANGLE_HEIGHT = 6
const val BLOCK = "#"
fun main() {
    for (rowNum in 1..RIGHT_TRIANGLE_HEIGHT) {
        print(BLOCK.repeat(rowNum))
        if (rowNum < RIGHT_TRIANGLE_HEIGHT) print('\n')
    }
}