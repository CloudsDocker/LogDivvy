package io.todzhang.tools.logdivvy.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HtmlController {

    @GetMapping("/")
    fun homePage(model: Model): String {
        model["title"] = "Greeting from Todd Zhang"
        return "todzhang"
    }
}

