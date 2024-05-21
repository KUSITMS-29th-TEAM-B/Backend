package com.bamyanggang.domainmodule.domain.tag.exception

import com.bamyanggang.commonmodule.exception.CustomException

sealed class TagException(
    errorCode: Int,
    message: String,
) : CustomException(CODE_PREFIX, errorCode, message) {

    class DuplicatedTagName(message: String = "태그 이름이 중복됩니다.") :
        TagException(errorCode = 1, message = message)

    class OverTagCountLimit(message: String = "태그 개수가 제한 개수보다 많습니다.") :
        TagException(errorCode = 2, message = message)

    class NotFoundTag(message: String = "존재하지 않는 태그 입니다.") :
        TagException(errorCode = 3, message = message)

    companion object {
        const val CODE_PREFIX = "TAG"
    }
}
