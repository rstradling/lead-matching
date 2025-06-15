package com.stradsw.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.stradsw"])
object Application {
    @JvmStatic
    fun main(args: Array<String>) {
        runApplication<Application>(*args)
    }
}
