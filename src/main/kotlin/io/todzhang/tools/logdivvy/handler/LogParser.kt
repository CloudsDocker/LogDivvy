package io.todzhang.tools.logdivvy.handler

import io.todzhang.tools.logdivvy.domain.LogEntry
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LogParser(
    @Autowired
    val patternHandler: PatternHandler,
    @Autowired
    val textLoader: FileContentHandler
) {
    private val logger = KotlinLogging.logger {}


    fun parseLogNow(): MutableList<LogEntry> {

        val resultList = mutableListOf<LogEntry>()
        patternHandler.init(null)
        textLoader.init("/Users/todd.zhang/Downloads/git_fetch_debug.log")
//
        val patterns = patternHandler.getPatterns()
        val linesRaw = textLoader.contentLines()
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

        var ptnIndex = 0;

        for (line in lines){
            if(ptnIndex>patterns.size-1){
                break
            }
            val pattern = patterns[ptnIndex]
            if (pattern.toRegex().containsMatchIn(line)) {
                logger.info { "Matched pattern: [[$pattern]] with line:==> $line" }
                resultList.add(LogEntry(ptnIndex, line))
                ptnIndex++
            }
        }

        return resultList
    }

    fun parseLogAsync(){}
}