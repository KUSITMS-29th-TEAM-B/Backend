package com.bamyanggang.apimodule

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [UserDetailsServiceAutoConfiguration::class])
class ApiModuleApplication {
}

fun main(args: Array<String>) {
    runApplication<ApiModuleApplication>(*args)
}
