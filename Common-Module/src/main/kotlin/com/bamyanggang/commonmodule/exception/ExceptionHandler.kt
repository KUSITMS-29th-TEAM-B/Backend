package com.bamyanggang.commonmodule.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(CustomException::class)
    fun handleJwtException(e: CustomException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse(e.code, e.message), e.httpStatusCode)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse("INTERNAL_SERVER_ERROR", "Internal Server Error"),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }
}

