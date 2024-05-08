package com.bamyanggang.commonmodule.exception

import org.springframework.http.HttpStatusCode

data class ErrorResponse(
    val code: String,
    val message: String,
    val httpStatusCode: HttpStatusCode
)
