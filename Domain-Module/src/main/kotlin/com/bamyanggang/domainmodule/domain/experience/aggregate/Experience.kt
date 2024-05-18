package com.bamyanggang.domainmodule.domain.experience.aggregate

import com.bamyanggang.domainmodule.common.entity.AggregateRoot
import com.example.uuid.UuidCreator
import java.time.LocalDateTime
import java.util.*

data class Experience(
    override val id: UUID = UuidCreator.create(),
    val userId : UUID,
    val title : String,
    val startedAt : LocalDateTime,
    val endedAt : LocalDateTime,
    val createdAt : LocalDateTime,
    val updatedAt : LocalDateTime,
) : AggregateRoot {
    init {
        require(title.length < 50) { "제목의 글자 수는 50자 제한입니다." }
    }
    companion object {
        fun create(
            title: String,
            userId: UUID,
            startedAt: LocalDateTime,
            endedAt: LocalDateTime,
        ): Experience {
            return Experience(
                userId = userId,
                title = title,
                startedAt = startedAt,
                endedAt = endedAt,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
            )
        }

        fun toDomain(
            id: UUID,
            userId: UUID,
            title: String,
            startedAt: LocalDateTime,
            endedAt: LocalDateTime,
            createdAt: LocalDateTime,
            updatedAt: LocalDateTime,
        ): Experience {
            return Experience(
                id = id,
                userId = userId,
                title = title,
                startedAt = startedAt,
                endedAt = endedAt,
                createdAt = createdAt,
                updatedAt = updatedAt,
            )
        }
    }
}
