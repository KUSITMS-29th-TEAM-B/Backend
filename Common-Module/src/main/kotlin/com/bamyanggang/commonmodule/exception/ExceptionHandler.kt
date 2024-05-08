package com.bamyanggang.commonmodule.exception

import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(CustomException::class)
    fun handleJwtException(e: CustomException): ErrorResponse{
        return ErrorResponse(e.code, e.message, e.httpStatusCode)
    }

}