fun summator(map: Map<Int, Int>): Int {
    // put your code here
    var  sum = 0

    for (item in map) {
        if (item.key % 2 ==0) {
            sum += item.value
        }
    }

    return sum
}