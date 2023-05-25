package svcs

fun main(args: Array<String>) {
    val commands = mapOf(
        "config" to "     Get and set a username.",
        "add" to "        Add a file to the index.",
        "log" to "        Show commit logs.",
        "commit" to "     Save changes.",
        "checkout" to "   Restore a file."
    )
    val argumentValue = if (args.isNotEmpty()) args[0].split(" ") else listOf()

    if (argumentValue.isEmpty() || argumentValue[0] == "--help") printHelp(commands)
    else println("'$argumentValue' is not a SVCS command.")
}

fun printHelp(content: Map<String, String>) {
    println("These are SVCS commands:")
    for (key:String in content.keys) println(key + content[key])
}
