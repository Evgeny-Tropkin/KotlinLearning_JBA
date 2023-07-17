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
                } else {
                    val user = getUserName(configFile)
                    if (user != null) {
                        println("The username is $user.")
                    }
                }
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
                else commit(args[1], indexFile, commitDirectory, logFile, configFile)
            }
            "checkout" -> {
                if (args.size == 1) println("Commit id was not passed.")
                else checkout(args[1], indexFile, logFile, commitDirectory)
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

fun getUserName(configFile: File): String? {
    val content = configFile.readLines()
    return if (content.isEmpty()) {
        println("Please, tell me who you are.")
        null
    } else content[0]
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
        for (filename in content) {
            println(File(filename).name)
        }
    }
}

fun addToIndex(indexFile: File, workingDirectoryPath: String, fileName: String) {
    val separator = File.separator
    val path = workingDirectoryPath + separator + fileName
    if (File(path).exists()) {
        val indexContent = indexFile.readLines()
        var fileIsNotInIndex = true
        for (name in indexContent) {
            if (name == path) fileIsNotInIndex = false
        }
        if (fileIsNotInIndex) {
            if (indexContent.isNotEmpty()) indexFile.appendText("\n")
            indexFile.appendText(path)
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

fun commit(message: String, index: File, commitsFolder: File, log: File, config: File) {
    val commitHash = getCommitHash(index)
    if (haveNotChanges(commitHash, log)) {
        println("Nothing to commit.")
        return
    }
    val folderForCommit = createCommitDirectory(commitsFolder, commitHash)
    copyTrackedFilesToCommitDirectory(folderForCommit, index)
    writeToLog(commitHash, getUserName(config), message, log)
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
    var folderName = commitHash
    val folder = commitsFolder.resolve(commitHash)
    if (folder.exists()) {
        folderName += "_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyy-MM-dd-HH-mm"))
    }
    return checkFolder(commitsFolder.absolutePath, folderName)
}

fun copyTrackedFilesToCommitDirectory(folderForCommit: File, index: File) {
    val listOfFiles = getFiles(index)
    for (file in listOfFiles){
        file.copyTo(folderForCommit.resolve(file.name))
    }
}

fun writeToLog(commit: String, author: String?, message: String, log: File) {
    val content = log.readLines()
    val currentCommitLog = "commit $commit\nAuthor: $author\n$message\n\n"
    log.writeText(currentCommitLog)
    log.appendText(content.joinToString("\n"))
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

fun checkout(commit: String, index: File, log: File, commitsDirectory: File) {
    val commitInfo = findCommitInLog(commit, log)
    if (commitInfo.isEmpty()) println("Commit does not exist.")
    else {
        copyFilesToWorkingDirectory(commitInfo[0], commitsDirectory, index)
    }
}

fun findCommitInLog(commit: String, log: File): MutableList<String> {
    val content = log.readLines()
    var res = mutableListOf<String>()

    for (i in content.indices) {
        if (content[i].startsWith("commit")) {
            val hash = content[i].substring(7)
            if (hash.startsWith(commit)) {
                res = mutableListOf(hash, content[i + 1], content[i + 2])
                break
            }
        }
    }
    return res
}

fun copyFilesToWorkingDirectory(hash: String, commitsFolder: File, index: File) {
    val files = index.readLines()
    val commitFolder = commitsFolder.resolve(hash)
    val commitedFiles = commitFolder.listFiles()?.toMutableList()
    for (path in files) {
        if (commitedFiles != null && commitedFiles.isEmpty().not()) {
            for (file in commitedFiles) {
                if (File(path).name == file.name) {
                    file.copyTo(File(path), true)
                    commitedFiles.remove(file)
                    break
                }
            }
        }
    }
    if (commitFolder.exists()) println("Switched to commit $hash.")
}