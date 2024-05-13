package com.bamyanggang.apimodule.domain.user.application.exception

import com.bamyanggang.commonmodule.exception.CustomException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

sealed class AuthException (
    errorCode: Int,
    httpStatusCode: HttpStatusCode,
    message: String,
) : CustomException(CODE_PREFIX, errorCode, httpStatusCode , message) {

        class OAuthFailed(message: String = "OAuth 인증에 실패하였습니다.") :
            AuthException(errorCode = 1, httpStatusCode = HttpStatus.BAD_REQUEST, message = message)
            
        class KakaoUserInfoRetrievalException(message: String = "카카오 사용자 정보를 가져오는데 실패했습니다.") :
            AuthException(errorCode = 2, httpStatusCode = HttpStatus.BAD_REQUEST, message = message)

        class GoogleUserInfoRetrievalException(message: String = "구글 사용자 정보를 가져오는데 실패했습니다.") :
            AuthException(errorCode = 3, httpStatusCode = HttpStatus.BAD_REQUEST, message = message)

        companion object {
            const val CODE_PREFIX = "AUTH"
        }

}