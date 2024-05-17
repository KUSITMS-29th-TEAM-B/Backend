package com.bamyanggang.domainmodule.domain.user.service

import com.bamyanggang.domainmodule.domain.user.aggregate.Token
import com.bamyanggang.domainmodule.domain.user.repository.TokenRepository
import org.springframework.stereotype.Service

@Service
class TokenRemover(
    private val tokenRepository: TokenRepository
) {
    fun removeToken(
        token: Token
    ) {
        tokenRepository.deleteByValue(token)
    }
}
