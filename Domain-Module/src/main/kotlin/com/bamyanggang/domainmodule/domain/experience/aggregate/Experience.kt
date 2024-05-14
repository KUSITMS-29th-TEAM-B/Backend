package com.bamyanggang.domainmodule.domain.experience.aggregate

import com.bamyanggang.domainmodule.common.entity.AggregateRoot
import com.example.uuid.UuidCreator
import java.time.LocalDateTime
import java.util.*

data class Experience(
    override val id: UUID = UuidCreator.create(),
    val userId : UUID,
    val parentTagId : UUID,
    val childTagId : UUID,
    val strongPointIds : List<UUID> = emptyList(),
    val title : String,
    val contentIds : List<UUID> = emptyList(),
    val startedAt : LocalDateTime,
    val endedAt : LocalDateTime,
    val createdAt : LocalDateTime,
    val updatedAt : LocalDateTime,
) : AggregateRoot {

    companion object {
        fun create(
            userId: UUID,
            parentTagId: UUID,
            childTagId: UUID,
            strongPointIds: List<UUID>,
            title: String,
            contentIds: List<UUID> = emptyList(),
            startedAt: LocalDateTime,
            endedAt: LocalDateTime,
        ): Experience {
            return Experience(
                id = UuidCreator.create(),
                userId = userId,
                parentTagId = parentTagId,
                childTagId = childTagId,
                strongPointIds = strongPointIds,
                title = title,
                contentIds = contentIds,
                startedAt = startedAt,
                endedAt = endedAt,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
            )
        }

        fun toDomain(
            id: UUID,
            userId: UUID,
            parentTagId: UUID,
            childTagId: UUID,
            strongPointIds: List<UUID>,
            title: String,
            contentIds: List<UUID> = emptyList(),
            startedAt: LocalDateTime,
            endedAt: LocalDateTime,
            createdAt: LocalDateTime,
            updatedAt: LocalDateTime,
        ): Experience {
            return Experience(
                id = id,
                userId = userId,
                parentTagId = parentTagId,
                childTagId = childTagId,
                strongPointIds = strongPointIds,
                title = title,
                contentIds = contentIds,
                startedAt = startedAt,
                endedAt = endedAt,
                createdAt = createdAt,
                updatedAt = updatedAt,
            )
        }
    }
}