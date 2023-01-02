package io.todzhang.tools.logdivvy.handler

import java.util.concurrent.atomic.AtomicBoolean

abstract class BaseHandler {
    abstract val inited: AtomicBoolean
}