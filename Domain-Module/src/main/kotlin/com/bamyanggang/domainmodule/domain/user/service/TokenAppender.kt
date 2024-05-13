package com.bamyanggang.domainmodule.domain.user.service

import com.bamyanggang.domainmodule.domain.user.aggregate.Token
import com.bamyanggang.domainmodule.domain.user.aggregate.User
import com.bamyanggang.domainmodule.domain.user.repository.TokenRepository
import org.springframework.stereotype.Service

@Service
class TokenAppender (
    private val tokenRepository: TokenRepository
) {
    fun appendToken(
        user: User,
        refreshToken: String,
    ): Token {
        return Token.create(
            user = user,
            value = refreshToken
        ).also { tokenRepository.save(it) }

    }
}