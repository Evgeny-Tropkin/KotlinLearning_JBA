// complete this function, add default values
fun carPrice(old: Int = 5, kilometers: Int = 100000, maximumSpeed: Int = 120, automatic: Boolean = false){
    val newPrice = 20000
    val automat = if (automatic) 1500 else 0
    val price = newPrice - old * 2000 + (maximumSpeed - 120) * 100 - (kilometers.div(10000)) * 200 + automat
    print(price)
}