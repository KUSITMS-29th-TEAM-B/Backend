package com.bamyanggang.domainmodule.domain.strongpoint.exception

import com.bamyanggang.commonmodule.exception.CustomException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

sealed class StrongPointException(
    errorCode: Int,
    httpStatusCode: HttpStatusCode,
    message: String,
) : CustomException(CODE_PREFIX, errorCode, httpStatusCode , message) {

    class DuplicatedStrongPointName(message: String = "역량 키워드 이름이 중복됩니다.") :
        StrongPointException(errorCode = 1, httpStatusCode = HttpStatus.BAD_REQUEST, message = message)

    companion object {
        const val CODE_PREFIX = "STRONG_POINT"
    }
}