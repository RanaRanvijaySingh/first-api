package com.firstapi.firstapi.helloworld

import com.firstapi.firstapi.helloworld.HelloWorldBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

// Controller
@RestController
class HelloWorldController {

    @Autowired
    lateinit var messageSource: MessageSource

    // GET
    // URI - /hello-world
    // method - "Hello World"
    @GetMapping(path = ["/hello-world"])
    fun helloWorld(): String {
        return "hello world"
    }

    @GetMapping(path = ["/hello-world-bean"])
    fun helloWorldBean(): HelloWorldBean {
        return HelloWorldBean("Hello world")
    }

    @GetMapping(path = ["/hello-world/path-variable/{name}"])
    fun helloWorldPathVariable(@PathVariable name: String): HelloWorldBean {
        return HelloWorldBean("Hello world $name")
    }

    @GetMapping(path = ["/hello-world-international"])
    fun helloWorldInternational(): HelloWorldBean {
        val message = messageSource.getMessage(
            "good.morning.message", null, "Default Value", LocaleContextHolder.getLocale()
        )
        return HelloWorldBean(message ?: "")
    }
}
