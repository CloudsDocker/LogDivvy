package io.todzhang.tools.logdivvy.handler

import org.springframework.stereotype.Component
import java.lang.RuntimeException
import java.util.concurrent.atomic.AtomicBoolean

@Component
class PatternHandler(override val inited: AtomicBoolean) : BaseHandler() {
    constructor() : this(AtomicBoolean(false))

    var patternSource: String? = null

    fun getPatterns(): List<String> {
        if (!this.inited.get()) {
            throw RuntimeException("Not inited")
        }
        return if (patternSource?.isNotBlank() == true) {
            listOf()
        } else {
            """
                debug\d*: Offering public key.*
                debug\d: Server accepts key:
                """.trimIndent().lines()
        }

    }

    fun init(source: String?) {
        this.patternSource = source
        this.inited.set(true)
    }

}