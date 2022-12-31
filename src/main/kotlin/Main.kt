import mu.KotlinLogging
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.readLines

fun main(args: Array<String>) {
    val logger = KotlinLogging.logger {}
    println("====!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
//    val srcString = "Offering public key"
    val srcString =
        "debug1: Offering public key: phray.zhang@gmail.com RSA SHA256:MtDXGqzrTishoYc9NDLZVMzSG4lPW7HJlVv4OZbzcqM agent"
//    val reg = "debug1: Offering public key?".toRegex(setOf(RegexOption.DOT_MATCHES_ALL, RegexOption.COMMENTS))
    val reg = "debug1: Offering public key.*".toRegex(setOf(RegexOption.DOT_MATCHES_ALL))
    val matches = reg.containsMatchIn(srcString)

//    val srcString = "Offering public key"
//    val reg = "Offering public key.*".toRegex(setOf(RegexOption.DOT_MATCHES_ALL, RegexOption.COMMENTS))

//    val srcString2 = "xabcd255f: sfsd"
////    val reg = """a[bc]+d?\W""".toRegex()
//    val regex2 = """abcd\d*:.*""".toRegex()
//    val matches2 = regex2.containsMatchIn(srcString2)
//    println("===matches:${matches2.toString()}")


//    println(matches?.groupValues)
//    println("===matches:${matches.toString()}")
//
    val patterns = getPatterns()
    val linesRaw = loadContentLines()
    val firstMatch = linesRaw.first { line ->
        patterns.firstOrNull()
            ?.let { println(it); it.toRegex() }
            ?.let { println(line); println(line.trim().matches(it)); line.trim().matches(it) } == true
    }
    println("==== FOUND:$firstMatch")

    val aa = patterns.firstOrNull()?.let {
        it.toRegex()
    }
    val startIndex = linesRaw.indexOfFirst { line ->
        aa?.containsMatchIn(line) == true
    }

    println("====first index of line is :$startIndex")

    val lines = linesRaw.subList(startIndex, linesRaw.size - 1)
    logger.info { "${lines.size}  lines of sublist of all content loaded " }

    var ptnIdex = 0;
    lines.forEach { line ->

        if(ptnIdex>patterns.size-1){
            
            
            return
        }
        val pattern = patterns[ptnIdex]
        if (pattern.toRegex().containsMatchIn(line)) {
            logger.info { "Matched pattern: [[$pattern]] with line:==> $line" }
            ptnIdex++
        }
    }

//    patterns.firstOrNull()? { ptn -> {
//        lines.first { line-> line.matches(ptn)}
//
//    }
//    }
}

fun loadContentLines(): List<String> {
    return Paths.get("/Users/todd.zhang/Downloads/git_fetch_debug.log").readLines()
}

/***
 * this should be either whole pattern regex or keyword as partial of whole line
 */
fun getPatterns(): List<String> {
    return """
    debug\d*: Offering public key.*
    debug\d: Server accepts key:
""".trimIndent().lines()
}
