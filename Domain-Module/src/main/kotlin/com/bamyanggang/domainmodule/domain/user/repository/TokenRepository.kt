package com.bamyanggang.domainmodule.domain.user.repository

import com.bamyanggang.domainmodule.domain.user.aggregate.Token

interface TokenRepository {
    fun save(token: Token)
}