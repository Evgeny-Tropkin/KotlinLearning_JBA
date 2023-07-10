package svcs

import java.io.File
import java.security.NoSuchAlgorithmException
import java.math.BigInteger
import java.security.MessageDigest
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main(args: Array<String>) {
    val commands = mapOf(
        "config" to "     Get and set a username.",
        "add" to "        Add a file to the index.",
        "log" to "        Show commit logs.",
        "commit" to "     Save changes.",
        "checkout" to "   Restore a file."
    )
    val separator = File.separator
    val workingDirectory = System.getProperty ("user.dir")
    val vcsFolder = checkFolder(workingDirectory, "vcs")
    val commitDirectory = checkFolder(workingDirectory + separator + "vcs", "commits")
    val configFile = checkFile(vcsFolder, "config.txt")
    val indexFile = checkFile(vcsFolder, "index.txt")
    val logFile = checkFile(vcsFolder, "log.txt")

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
            "log" -> {
                for (str in getLog(logFile)) println(str)
            }
            "commit" -> {
                if (args.size == 1) println("Message was not passed.")
                else commit(args[1], indexFile, commitDirectory, logFile)
            }
            else -> println(commands[args[0]])
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
    } else println("Can't find '$fileName'.")
}

fun getLog(logFile: File): List<String> {
    val content = logFile.readLines()
    if (content.isEmpty()) {
        return listOf("No commits yet.")
    }
    return content
}

fun commit(message: String, index: File, commitsFolder: File, logFile: File) {
    val commitHash = getCommitHash(index)
    if (haveNotChanges(commitHash, logFile)) {
        println("Nothing to commit.")
        return
    }
    val folderForCommit = createCommitDirectory(commitsFolder, commitHash)
    copyTrackedFilesToCommitDirectory(folderForCommit, index)
    writeToLog(message)
    println("Changes are committed.")
}

fun getCommitHash(index: File): String {
    val indexedFiles = getFiles(index)
    val joinedByteContent = getByteContent(indexedFiles)
    return hash(joinedByteContent)
}

fun haveNotChanges(commitHash: String, logFile: File): Boolean {
    val log = getLog(logFile)
    if (log[0].startsWith("commit")){
        return log[0].split(" ")[1] == commitHash
    }
    return false
}

fun createCommitDirectory(commitsFolder: File, commitHash: String): File {
    val separator = File.separator
    var folder = File(commitsFolder.toString() + separator + commitHash)
    if (folder.exists()) {
        folder = File(folder.toString() + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyy-MM-dd-HH-mm")))
    }
    folder.mkdir()
    return folder
}

fun copyTrackedFilesToCommitDirectory(folderForCommit: File, index: File) {
    val listOfFiles = getFiles(index)
    for (file in listOfFiles){
        file.copyTo(folderForCommit)
    }
}

fun writeToLog(message: String) {
    return
}

fun getFiles(fileWithList: File): MutableList<File> {
    val content = fileWithList.readLines()
    val files = mutableListOf<File>()
    for (path in content) {
        files.add(File(path))
    }
    return files
}

fun getByteContent(listOfFiles: MutableList<File>): ByteArray {
    val totalSize = listOfFiles.sumOf { file -> file.length().toInt() }
    val first = listOfFiles[0].readBytes()

    val dest: ByteArray = first.copyOf(totalSize)
    var destPos = first.size
    for (i in 1 until listOfFiles.size) {
        val array = listOfFiles[i].readBytes()
        System.arraycopy(array, 0, dest, destPos, array.size)
        destPos += array.size
    }
    return dest
}

fun hash(bytedContent: ByteArray): String {
    return try {
        val md = MessageDigest.getInstance("SHA-256")
        val messageDigest = md.digest(bytedContent)
        val no = BigInteger(1, messageDigest)
        var hashtext = no.toString(16)
        while (hashtext.length < 32) {
            hashtext = "0$hashtext"
        }
        hashtext
    } catch (e: NoSuchAlgorithmException) {
        throw RuntimeException(e)
    }
}