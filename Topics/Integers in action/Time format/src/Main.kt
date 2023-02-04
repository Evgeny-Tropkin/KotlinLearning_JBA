fun main() {
    val totalSeconds = System.currentTimeMillis() / 1000 // dont change this line
    // solution
    val secondsFromMidnight = totalSeconds % (24 * 3600)
    val hours = secondsFromMidnight / 3600
    val minutes = (secondsFromMidnight % 3600) / 60
    val seconds = secondsFromMidnight % 60

    println("$hours:$minutes:$seconds")
}