package com.bamyanggang.domainmodule.domain.strongpoint.exception

import com.bamyanggang.commonmodule.exception.CustomException
import com.bamyanggang.domainmodule.domain.strongpoint.aggregate.StrongPoint
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

sealed class StrongPointException(
    errorCode: Int,
    httpStatusCode: HttpStatusCode,
    message: String,
) : CustomException(CODE_PREFIX, errorCode, httpStatusCode , message) {

    class DuplicatedName(message: String = "역량 키워드 이름이 중복됩니다.") :
        StrongPointException(errorCode = 1, httpStatusCode = HttpStatus.BAD_REQUEST, message = message)

    class NotFoundStrongPoint(message: String = "존재하지 않는 역량 키워드 입니다.") :
        StrongPointException(errorCode = 2, httpStatusCode = HttpStatus.NOT_FOUND, message = message)

    class OverCountLimit (message: String = "역량 키워드는 최대 ${StrongPoint.LIMIT}개까지 등록 가능합니다.") :
    StrongPointException(errorCode = 3, httpStatusCode = HttpStatus.BAD_REQUEST, message = message)

    companion object {
        const val CODE_PREFIX = "STRONG_POINT"
    }
}
