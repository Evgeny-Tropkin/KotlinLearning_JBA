// TODO: provide three functions here

fun generate(functionName: String): (Int) -> Int {
    when (functionName) {
        "identity" -> return { x -> x }
        "half" -> return { x -> x / 2 }
        else -> return { _ -> 0 }
    }
}