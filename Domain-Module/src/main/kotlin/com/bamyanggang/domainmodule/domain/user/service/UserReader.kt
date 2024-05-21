package com.bamyanggang.domainmodule.domain.user.service

import com.bamyanggang.domainmodule.domain.user.aggregate.User
import com.bamyanggang.domainmodule.domain.user.repository.UserRepository
import java.util.UUID

class UserReader(
    private val userRepository: UserRepository
) {
    fun readUserBySocialId(socialId: String): User? {
        return userRepository.findBySocialId(socialId)
    }

    fun readUserById(userId: UUID): User {
        return userRepository.findById(userId)
    }
}
