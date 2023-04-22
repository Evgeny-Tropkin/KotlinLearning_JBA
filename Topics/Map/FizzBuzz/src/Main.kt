fun iterator(map: Map<String, Int>) {
    // put your code here
    for (item in map) {
        if (item.value % 3 == 0) println("Fizz")
        else if (item.value % 5 == 0) println("Buzz")
        else println("FizzBuzz")
    }
}