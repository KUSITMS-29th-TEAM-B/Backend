package com.bamyanggang.domainmodule.domain.user.service

import com.bamyanggang.domainmodule.domain.user.aggregate.User
import com.bamyanggang.domainmodule.domain.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserReader(
    private val userRepository: UserRepository
) {
    fun readUserBySocialId(socialId: String): User? {
        return userRepository.findBySocialId(socialId)
    }
}