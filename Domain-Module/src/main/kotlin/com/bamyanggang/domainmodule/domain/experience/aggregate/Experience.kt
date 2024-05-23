package com.bamyanggang.domainmodule.domain.experience.aggregate

import com.bamyanggang.domainmodule.common.entity.AggregateRoot
import com.example.uuid.UuidCreator
import java.time.LocalDateTime
import java.util.*

data class Experience(
    override val id: UUID = UuidCreator.create(),
    val title : String,
    val userId : UUID,
    val parentTagId: UUID,
    val childTagId: UUID,
    val strongPoints: List<ExperienceStrongPoint> = emptyList(),
    val contents: List<ExperienceContent>,
    val startedAt : LocalDateTime,
    val endedAt : LocalDateTime,
    val createdAt : LocalDateTime,
    val updatedAt : LocalDateTime,
) : AggregateRoot {
    fun update(
        title: String,
        userId: UUID,
        parentTagId: UUID,
        childTagId: UUID,
        contents: List<ExperienceContent>,
        experienceStrongPoints: List<ExperienceStrongPoint>,
        startedAt: LocalDateTime,
        endedAt: LocalDateTime,
    ): Experience {
        return copy(
            title = title,
            userId = userId,
            parentTagId = parentTagId,
            childTagId = childTagId,
            contents = contents,
            strongPoints = experienceStrongPoints,
            startedAt = startedAt,
            endedAt = endedAt,
            createdAt = createdAt,
            updatedAt = LocalDateTime.now(),
        )
    }

    init {
        require(title.length < 50) { "제목의 글자 수는 50자 제한입니다." }
        require(startedAt.isBefore(endedAt)) { "활동 시작일은 종료일보다 빨라야 합니다."}
    }

    companion object {
        fun create(
            title: String,
            userId: UUID,
            parentTagId: UUID,
            childTagId: UUID,
            contents: List<ExperienceContent>,
            experienceStrongPoints: List<ExperienceStrongPoint>,
            startedAt: LocalDateTime,
            endedAt: LocalDateTime,
        ): Experience {
            return Experience(
                userId = userId,
                title = title,
                parentTagId = parentTagId,
                childTagId = childTagId,
                contents = contents,
                strongPoints = experienceStrongPoints,
                startedAt = startedAt,
                endedAt = endedAt,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
            )
        }
    }
}
