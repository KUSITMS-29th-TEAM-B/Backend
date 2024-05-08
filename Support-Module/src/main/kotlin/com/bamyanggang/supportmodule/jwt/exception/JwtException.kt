package com.bamyanggang.supportmodule.jwt.exception

import com.bamyanggang.commonmodule.exception.CustomException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

sealed class JwtException(
    errorCode: Int,
    httpStatusCode: HttpStatusCode,
    message: String,
) : CustomException(CODE_PREFIX, errorCode, httpStatusCode , message) {

        class InvalidTokenException(message: String = "유효하지 않은 토큰입니다.") :
            JwtException(errorCode = 1, httpStatusCode = HttpStatus.BAD_REQUEST, message = message)

        class ExpiredTokenException(message: String = "만료된 토큰입니다.") :
            JwtException(errorCode = 2, httpStatusCode = HttpStatus.BAD_REQUEST, message = message)

        companion object {
            const val CODE_PREFIX = "JWT"
        }

}