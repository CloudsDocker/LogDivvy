package io.todzhang.tools.logdivvy.handler

import java.util.concurrent.atomic.AtomicBoolean

interface IHandler {
    val inited: AtomicBoolean
}