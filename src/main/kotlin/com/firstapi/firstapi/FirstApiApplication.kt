package com.firstapi.firstapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FirstApiApplication

fun main(args: Array<String>) {
	runApplication<FirstApiApplication>(*args)
}
