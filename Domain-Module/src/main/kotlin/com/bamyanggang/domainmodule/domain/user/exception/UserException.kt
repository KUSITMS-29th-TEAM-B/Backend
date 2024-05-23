package com.bamyanggang.domainmodule.domain.user.exception

import com.bamyanggang.commonmodule.exception.CustomException

sealed class UserException(
    errorCode: Int,
    message: String,
) : CustomException(CODE_PREFIX, errorCode, message) {

    class DuplicatedUser(message: String = "이미 존재하는 유저입니다.") :
        UserException(errorCode = 1, message = message)

    companion object {
        const val CODE_PREFIX = "USER"
    }
}
