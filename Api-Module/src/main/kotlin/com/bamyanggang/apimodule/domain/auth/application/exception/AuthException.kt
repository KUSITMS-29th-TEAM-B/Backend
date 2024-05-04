package com.bamyanggang.apimodule.domain.auth.application.exception

import com.bamyanggang.commonmodule.exception.CustomException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

sealed class AuthException (
    errorCode: Int,
    httpStatusCode: HttpStatusCode,
    message: String,
) : CustomException(CODE_PREFIX, errorCode, httpStatusCode , message) {

        class OAuthFailed(message: String = "OAuth 인증에 실패하였습니다.") :
            AuthException(errorCode = 1001, httpStatusCode = HttpStatus.BAD_REQUEST, message = message)

        class KakaoUserInfoRetrievalException(message: String = "카카오 사용자 정보를 가져오는데 실패했습니다.") :
            AuthException(errorCode = 1002, httpStatusCode = HttpStatus.BAD_REQUEST, message = message)

        companion object {
            const val CODE_PREFIX = "AUTH"
        }

}