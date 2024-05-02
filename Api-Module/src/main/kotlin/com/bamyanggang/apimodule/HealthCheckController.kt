package com.bamyanggang.apimodule

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckController {
    @GetMapping("/")
    fun healthCheck(): String {
        println("call health check")
        return "OK"
    }
}