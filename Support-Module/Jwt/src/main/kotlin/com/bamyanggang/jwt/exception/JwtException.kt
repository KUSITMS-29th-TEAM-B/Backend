package com.bamyanggang.jwt.exception

import com.bamyanggang.commonmodule.exception.CustomException

sealed class JwtException(
    errorCode: Int,
    message: String,
) : CustomException(CODE_PREFIX, errorCode, message) {

        class InvalidTokenException(message: String = "유효하지 않은 토큰입니다.") :
            JwtException(errorCode = 1, message = message)

        class ExpiredTokenException(message: String = "만료된 토큰입니다.") :
            JwtException(errorCode = 2, message = message)

        class TokenNotFoundException(message: String = "토큰이 존재하지 않습니다.") :
            JwtException(errorCode = 3, message = message)

        companion object {
            const val CODE_PREFIX = "JWT"
        }

}
