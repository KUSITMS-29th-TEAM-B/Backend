package com.bamyanggang.commonmodule.exception

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    private val logger = LoggerFactory.getLogger(ExceptionHandler::class.java)
    @ExceptionHandler(CustomException::class)
    fun handleJwtException(e: CustomException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse(e.code, e.message), e.httpStatusCode)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ResponseEntity<ErrorResponse> {
        logger.error("Unhandled exception", exception)
        return ResponseEntity(
            ErrorResponse("INTERNAL_SERVER_ERROR", "Internal Server Error"),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(exception: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse("BAD_REQUEST", exception.message ?: "Bad Request"),
            HttpStatus.BAD_REQUEST
        )
    }
}

