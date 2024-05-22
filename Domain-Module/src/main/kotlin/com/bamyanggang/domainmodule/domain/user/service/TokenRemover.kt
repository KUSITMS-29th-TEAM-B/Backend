package com.bamyanggang.domainmodule.domain.user.service

import com.bamyanggang.domainmodule.domain.user.aggregate.Token
import com.bamyanggang.domainmodule.domain.user.repository.TokenRepository

class TokenRemover(
    private val tokenRepository: TokenRepository
) {
    fun removeToken(
        token: String
    ) {
        tokenRepository.deleteByValue(token)
    }

}
