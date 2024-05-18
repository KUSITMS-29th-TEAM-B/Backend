package com.bamyanggang.domainmodule.domain.user.aggregate

import com.bamyanggang.domainmodule.common.entity.DomainEntity
import com.example.uuid.UuidCreator
import java.time.LocalDateTime
import java.util.*

class Token(
    override val id: UUID = UuidCreator.create(),
    val userId: UUID,
    val value: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    ): DomainEntity {

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

        fun toDomain(
            id: UUID,
            userId: UUID,
            value: String,
            createdAt: LocalDateTime,
            updatedAt: LocalDateTime,
        ): Token {
            return Token(
                id = id,
                userId = userId,
                value = value,
                createdAt = createdAt,
                updatedAt = updatedAt,
            )
        }
    }

}
