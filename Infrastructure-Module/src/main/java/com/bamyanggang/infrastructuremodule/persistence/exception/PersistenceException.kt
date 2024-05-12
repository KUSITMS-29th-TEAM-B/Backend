package com.bamyanggang.infrastructuremodule.persistence.exception

import com.bamyanggang.commonmodule.exception.CustomException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

sealed class PersistenceException(
    errorCode: Int,
    httpStatusCode: HttpStatusCode,
    message: String,
) : CustomException(CODE_PREFIX, errorCode, httpStatusCode , message) {

    class EntityNotFoundException(message: String = "Entity를 찾을 수 없습니다.") :
        PersistenceException(errorCode = 1, httpStatusCode = HttpStatus.NOT_FOUND, message = message)

    class EntityAlreadyExistsException(message: String = "Entity가 이미 존재합니다.") :
        PersistenceException(errorCode = 2, httpStatusCode = HttpStatus.CONFLICT, message = message)

    companion object {
        const val CODE_PREFIX = "PERSISTENCE"
    }

}