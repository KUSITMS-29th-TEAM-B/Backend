package com.bamyanggang.apimodule.common

import com.bamyanggang.domainmodule.domain.user.aggregate.User
import com.bamyanggang.domainmodule.domain.user.service.UserReader
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserUtils(private val userReader: UserReader) {

    fun getAccessUser(): User {
        val userId = SecurityUtils.getAuthenticationPrincipal()
        return userReader.readUserById(userId)
    }

    fun getIdFromAccessUser(): UUID {
        return SecurityUtils.getAuthenticationPrincipal()
    }
}