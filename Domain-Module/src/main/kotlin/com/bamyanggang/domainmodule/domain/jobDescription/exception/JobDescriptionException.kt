package com.bamyanggang.domainmodule.domain.jobDescription.exception

import com.bamyanggang.commonmodule.exception.CustomException

sealed class JobDescriptionException (
    errorCode: Int,
    message: String,
) : CustomException(CODE_PREFIX, errorCode, message) {

    class ModifyWriteStatusFailed(message: String = "쓰기 상태 변경에 실패하였습니다.") :
        JobDescriptionException(errorCode = 1, message = message)

    companion object {
        const val CODE_PREFIX = "AUTH"
    }

}
