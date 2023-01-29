package svcs

fun main(args: Array<String>) {
    val helpContent = mapOf(
        "config" to "     Get and set a username.",
        "add" to "        Add a file to the index.",
        "log" to "        Show commit logs.",
        "commit" to "     Save changes.",
        "checkout" to "   Restore a file."
    )
    val helpArgumentValue = if (args.isNotEmpty()) args[0] else ""
    if (helpArgumentValue in helpContent) println(helpContent[helpArgumentValue])
    else if (helpArgumentValue == "--help" || helpArgumentValue.isEmpty()) printHelp(helpContent)
    else println("'$helpArgumentValue' is not a SVCS command.")
}

fun printHelp(content: Map<String, String>) {
    println("These are SVCS commands:")
    for (key:String in content.keys) println(key + content[key])
}
