fun main() {
    val dividend: Int = readln().toInt()
    val divisor: Int = readln().toInt()

    val quotient: Int = dividend.floorDiv(divisor)
    val remainder: Int = dividend.mod(divisor)

    print("The quotient is: $quotient and the remainder is: $remainder")
}