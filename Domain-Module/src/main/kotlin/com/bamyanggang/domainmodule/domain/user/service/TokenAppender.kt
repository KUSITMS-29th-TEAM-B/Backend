package com.bamyanggang.domainmodule.domain.user.service

import com.bamyanggang.domainmodule.domain.user.aggregate.Token
import com.bamyanggang.domainmodule.domain.user.repository.TokenRepository
import java.util.*

class TokenAppender (
    private val tokenRepository: TokenRepository
) {
    fun appendToken(
        userId: UUID,
        refreshToken: String,
    ) {
        val existingToken = tokenRepository.findByUserId(userId)
        existingToken?.let {
            val updatedToken = it.update(userId = userId, refreshToken = refreshToken)
            tokenRepository.save(updatedToken)
        } ?: Token.create(
            userId = userId,
            value = refreshToken
        ).also { tokenRepository.save(it) }
    }

}
