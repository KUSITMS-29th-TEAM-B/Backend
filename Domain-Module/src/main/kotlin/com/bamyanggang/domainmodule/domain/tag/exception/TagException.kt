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

    companion object {
        const val CODE_PREFIX = "TAG"
    }
}
