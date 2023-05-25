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
    val workingDirectory = System.getProperty ("user.dir")
    val vcsFolder = checkFolder(workingDirectory, "vcs")
    val configFile = checkFile(vcsFolder, "config.txt")
    val indexFile = checkFile(vcsFolder, "index.txt")

    if (args.isEmpty() || args[0] == "--help") printHelp(commands)
    else if (args[0] in commands) {
        when (args[0]) {
            "config" -> {
                if (args.size > 1) {
                    setUserName(configFile, args[1])
                } else getUserName(configFile)
            }
            "add" -> {
                if (args.size > 1) {
                    addToIndex(indexFile, workingDirectory, args[1])
                } else getIndex(indexFile)
            }
        }
    }
    else println("'${args[0]}' is not a SVCS command.")
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

fun checkFile(folder: File, fileName:String): File {
    val file = folder.resolve(fileName)
    if (!file.exists()) {
        file.createNewFile()
    }
    return file
}

fun getUserName(configFile: File) {
    val content = configFile.readLines()
    if (content.isEmpty()) {
        println("Please, tell me who you are.")
    } else println("The username is ${content[0]}.")
}

fun setUserName(configFile: File, userName: String){
    configFile.writeText(userName)
    println("The username is $userName.")
}

fun getIndex(indexFile:File) {
    val content = indexFile.readLines()
    if (content.isEmpty()){
        println("Add a file to the index.")
    } else {
        println("Tracked files:")
        for (filename in content) println(filename)
    }
}

fun addToIndex(indexFile: File, workingDirectoryPath: String, fileName: String) {
    val separator = File.separator
    if (File(workingDirectoryPath + separator + fileName).exists()) {
        val indexContent = indexFile.readLines()
        var fileIsNotInIndex = true
        for (name in indexContent) {
            if (name == fileName) fileIsNotInIndex = false
        }
        if (fileIsNotInIndex) {
            if (indexContent.isNotEmpty()) indexFile.appendText("\n")
            indexFile.appendText(fileName)
        }
        println("The file '$fileName' is tracked.")
    } else println("Can't find '$fileName'")
}