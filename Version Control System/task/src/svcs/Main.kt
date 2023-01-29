package svcs

fun main() {
    val helpContent = mapOf(
        "config" to "Get and set a username.",
        "add" to "Add a file to the index.",
        "log" to "Show commit logs.",
        "commit" to "Save changes.",
        "checkout" to "Restore a file."
    )
    val helpArgumentValue = readln()
    if (helpArgumentValue in helpContent) println(helpContent[helpArgumentValue])
    else if (helpArgumentValue == "--help" || helpArgumentValue.isEmpty()) printHelp(helpContent)
}

fun printHelp(content: Map<String, String>) {
    println("These are SVCS commands:")
    for (key:String in content.keys) println(content[key])
}
