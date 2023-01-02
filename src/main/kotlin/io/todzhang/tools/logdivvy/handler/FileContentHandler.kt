package io.todzhang.tools.logdivvy.handler

import org.springframework.stereotype.Component
import java.lang.RuntimeException
import java.nio.file.Paths
import kotlin.io.path.readLines

@Component
class FileContentHandler : ContentHandler {
    private lateinit var path: String

    override fun contentLines(): List<String> {
        val lines = Paths.get(path).readLines()

        if (lines.isNullOrEmpty()) {
            throw RuntimeException("Empty content in path: $path")
        }
        return lines
    }

    fun init(path: String) {
        this.path = path
    }

}
