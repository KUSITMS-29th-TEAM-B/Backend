package com.bamyanggang.domainmodule.domain.strongpoint.exception

import com.bamyanggang.commonmodule.exception.CustomException
import com.bamyanggang.domainmodule.domain.strongpoint.aggregate.StrongPoint

sealed class StrongPointException(
    errorCode: Int,
    message: String,
) : CustomException(CODE_PREFIX, errorCode, message) {

    class OverCountLimit (message: String = "역량 키워드는 최대 ${StrongPoint.LIMIT}개까지 등록 가능합니다.") :
    StrongPointException(errorCode = 3, message = message)

    companion object {
        const val CODE_PREFIX = "STRONG_POINT"
    }
}
