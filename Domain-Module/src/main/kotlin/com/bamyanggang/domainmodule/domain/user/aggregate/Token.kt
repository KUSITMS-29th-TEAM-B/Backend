package com.bamyanggang.domainmodule.domain.user.aggregate

import com.bamyanggang.domainmodule.common.entity.DomainEntity
import com.example.uuid.UuidCreator
import java.time.LocalDateTime
import java.util.*

data class Token(
    override val id: UUID = UuidCreator.create(),
    val userId: UUID,
    val value: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    ): DomainEntity {

    fun update(userId: UUID, refreshToken: String): Token {
        return copy(
            userId = userId,
            value = refreshToken,
            updatedAt = LocalDateTime.now()
        )
    }

    companion object {
        fun create(
            userId: UUID,
            value: String,
        ): Token {
            return Token(
                userId = userId,
                value = value,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
            )
        }
    }

}
