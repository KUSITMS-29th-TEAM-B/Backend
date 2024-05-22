package com.bamyanggang.domainmodule.domain.user.repository

import com.bamyanggang.domainmodule.domain.user.aggregate.Token
import java.util.UUID

interface TokenRepository {
    fun save(token: Token)
    fun findByValue(value: String): Token
    fun deleteByValue(value: String)
    fun findByUserId(userId: UUID): Token?

}
