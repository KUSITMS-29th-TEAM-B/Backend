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
    init {
        require(title.length < 50) { "제목의 글자 수는 50자 제한입니다." }
        require(strongPointIds.size <= 5) { "역량 키워드는 최대 5개까지 붙일 수 있습니다."}
    }
    companion object {
        fun create(
            title: String,
            userId: UUID,
            parentTagId: UUID,
            childTagId: UUID,
            strongPointIds: List<UUID>,
            contentIds: List<UUID> = emptyList(),
            startedAt: LocalDateTime,
            endedAt: LocalDateTime,
        ): Experience {
            return Experience(
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
