package com.bamyanggang.domainmodule.domain.jobDescription.exception

import com.bamyanggang.commonmodule.exception.CustomException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

sealed class JobDescriptionException (
    errorCode: Int,
    httpStatusCode: HttpStatusCode,
    message: String,
) : CustomException(CODE_PREFIX, errorCode, httpStatusCode , message) {

    class ModifyWriteStatusFailed(message: String = "쓰기 상태 변경에 실패하였습니다.") :
        JobDescriptionException(errorCode = 1, httpStatusCode = HttpStatus.BAD_REQUEST, message = message)

    companion object {
        const val CODE_PREFIX = "AUTH"
    }

}
