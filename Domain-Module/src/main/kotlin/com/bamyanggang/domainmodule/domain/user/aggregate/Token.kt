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
            user: User,
            value: String,
        ): Token {
            return Token(
                userId = user.id,
                value = value,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
            )
        }
    }
}