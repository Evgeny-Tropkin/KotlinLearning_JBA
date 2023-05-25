package svcs

import java.io.File

fun main(args: Array<String>) {
    val commands = mapOf(
        "config" to "     Get and set a username.",
        "add" to "        Add a file to the index.",
        "log" to "        Show commit logs.",
        "commit" to "     Save changes.",
        "checkout" to "   Restore a file."
    )
    val argumentValue = if (args.isNotEmpty()) args[0].split(" ") else listOf()
    val workingDirectory = System.getProperty ("user.dir") + File.separator + "Version Control System"
    val vcsFolder = checkFolder(workingDirectory, "vcs")

    if (argumentValue.isEmpty() || argumentValue[0] == "--help") printHelp(commands)
    else println("'${argumentValue[0]}' is not a SVCS command.")
}

fun printHelp(content: Map<String, String>) {
    println("These are SVCS commands:")
    for (key:String in content.keys) println(key + content[key])
}

fun checkFolder(path: String, folderName: String): File {
    val separator = File.separator
    val folder = File(path + separator + folderName)
    if (!folder.exists()) {
        folder.mkdir()
    }
    return folder
}
