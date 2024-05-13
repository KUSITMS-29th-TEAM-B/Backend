package com.bamyanggang.domainmodule.domain.experience.aggregate

import com.bamyanggang.domainmodule.common.entity.AggregateRoot
import com.bamyanggang.domainmodule.domain.strongpoint.aggregate.StrongPoint
import com.example.uuid.UuidCreator
import java.time.LocalDateTime
import java.util.*

data class Experience(
    override val id: UUID = UuidCreator.create(),
    val userId : UUID,
    val parentTag : Tag,
    val childTag : Tag,
    val strongPoints : List<StrongPoint> = emptyList(),
    val title : String,
    val contents : List<ExperienceContent> = emptyList(),
    val startedAt : LocalDateTime,
    val endedAt : LocalDateTime,
    val createdAt : LocalDateTime,
    val updatedAt : LocalDateTime,
) : AggregateRoot {

    companion object {
        fun create(
            userId: UUID,
            parentTag: Tag,
            childTag: Tag,
            strongPoints: List<StrongPoint>,
            title: String,
            contents: List<ExperienceContent> = emptyList(),
            startedAt: LocalDateTime,
            endedAt: LocalDateTime,
        ): Experience {
            return Experience(
                id = UuidCreator.create(),
                userId = userId,
                parentTag = parentTag,
                childTag = childTag,
                strongPoints = strongPoints,
                title = title,
                contents = contents,
                startedAt = startedAt,
                endedAt = endedAt,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
            )
        }

        fun toDomain(
            id: UUID,
            userId: UUID,
            parentTag: Tag,
            childTag: Tag,
            strongPoints: List<StrongPoint>,
            title: String,
            contents: List<ExperienceContent> = emptyList(),
            startedAt: LocalDateTime,
            endedAt: LocalDateTime,
            createdAt: LocalDateTime,
            updatedAt: LocalDateTime,
        ): Experience {
            return Experience(
                id = id,
                userId = userId,
                parentTag = parentTag,
                childTag = childTag,
                strongPoints = strongPoints,
                title = title,
                contents = contents,
                startedAt = startedAt,
                endedAt = endedAt,
                createdAt = createdAt,
                updatedAt = updatedAt,
            )
        }
    }
}