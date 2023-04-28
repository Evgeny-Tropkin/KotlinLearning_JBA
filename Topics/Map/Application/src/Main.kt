fun bill(priceList: Map<String, Int>, shoppingList: MutableList<String>): Int {
    // put your code here
    var sum = 0
    for (item in shoppingList) {
        if (priceList.containsKey(item)) sum += priceList[item]!!
    }
    return sum
}