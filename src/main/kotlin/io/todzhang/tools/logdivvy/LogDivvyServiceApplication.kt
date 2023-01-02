package io.todzhang.tools.logdivvy

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LogDivvyServiceApplication

fun main(args: Array<String>) {
    runApplication<LogDivvyServiceApplication>(*args){
        setBannerMode(Banner.Mode.LOG)
    }
}