package com.bamyanggang.domainmodule.domain.jobDescription.aggregate

import com.bamyanggang.domainmodule.common.entity.AggregateRoot
import com.example.uuid.UuidCreator
import java.time.LocalDateTime
import java.util.*

data class JobDescription(
    override val id: UUID = UuidCreator.create(),
    val enterpriseName: String,
    val title: String,
    val content: String,
    val link: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val startedAt: LocalDateTime,
    val endedAt: LocalDateTime,
    val userId: UUID,
    ) : AggregateRoot{

    init {
        require(enterpriseName.isNotBlank()) { "기업명은 필수입니다." }
        require(title.isNotBlank()) { "제목은 필수입니다." }
        require(content.isNotBlank()) { "내용은 필수입니다." }
        require(link.isNotBlank()) { "링크는 필수입니다." }
        require(startedAt.isBefore(endedAt)) { "시작일은 종료일보다 빨라야 합니다." }
    }

    companion object {
        fun create(
            enterpriseName: String,
            title: String,
            content: String,
            link: String,
            startedAt: LocalDateTime,
            endedAt: LocalDateTime,
            userId: UUID
        ): JobDescription {
            return JobDescription(
                enterpriseName = enterpriseName,
                title = title,
                content = content,
                link = link,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
                startedAt = startedAt,
                endedAt = endedAt,
                userId = userId
            )
        }

        fun toDomain(
            id: UUID,
            enterpriseName: String,
            title: String,
            content: String,
            link: String,
            createdAt: LocalDateTime,
            updatedAt: LocalDateTime,
            startedAt: LocalDateTime,
            endedAt: LocalDateTime,
            userId: UUID,
        ): JobDescription {
            return JobDescription(
                id = id,
                enterpriseName = enterpriseName,
                title = title,
                content = content,
                link = link,
                createdAt = createdAt,
                updatedAt = updatedAt,
                startedAt = startedAt,
                endedAt = endedAt,
                userId = userId,
            )
        }
    }

}
