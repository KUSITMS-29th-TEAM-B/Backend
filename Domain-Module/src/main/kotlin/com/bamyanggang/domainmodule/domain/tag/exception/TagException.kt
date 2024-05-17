package com.bamyanggang.domainmodule.domain.tag.exception

import com.bamyanggang.commonmodule.exception.CustomException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

sealed class TagException(
    errorCode: Int,
    httpStatusCode: HttpStatusCode,
    message: String,
) : CustomException(CODE_PREFIX, errorCode, httpStatusCode , message) {

    class DuplicatedTagName(message: String = "태그 이름이 중복됩니다.") :
        TagException(errorCode = 1, httpStatusCode = HttpStatus.BAD_REQUEST, message = message)

    class OverTagCountLimit(message: String = "태그 개수가 제한 개수보다 많습니다.") :
        TagException(errorCode = 2, httpStatusCode = HttpStatus.BAD_REQUEST, message = message)

    class NotFoundTag(message: String = "존재하지 않는 태그 입니다.") :
        TagException(errorCode = 3, httpStatusCode = HttpStatus.BAD_REQUEST, message = message)

    companion object {
        const val CODE_PREFIX = "TAG"
    }
}
