package com.bamyanggang.domainmodule.domain.user.service

import com.bamyanggang.domainmodule.domain.user.aggregate.Token
import com.bamyanggang.domainmodule.domain.user.repository.TokenRepository
import java.util.*

class TokenModifier(
    private val tokenRepository: TokenRepository
) {

    fun modifyToken(
        userId: UUID,
        refreshToken: String,
        newRefreshToken: String
    ): Token {
        return tokenRepository
            .findByValue(refreshToken)
            .update(
                userId = userId,
                refreshToken = newRefreshToken
            )
            .also { tokenRepository.save(it) }
    }

}
