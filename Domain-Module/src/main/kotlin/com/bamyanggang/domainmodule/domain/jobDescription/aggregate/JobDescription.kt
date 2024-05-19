package com.bamyanggang.domainmodule.domain.jobDescription.aggregate

import com.bamyanggang.domainmodule.common.entity.AggregateRoot
import com.bamyanggang.domainmodule.domain.jobDescription.enums.WriteStatus
import com.bamyanggang.domainmodule.domain.jobDescription.exception.JobDescriptionException
import com.example.uuid.UuidCreator
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*

data class JobDescription(
    override val id: UUID = UuidCreator.create(),
    val enterpriseName: String,
    val title: String,
    val writeStatus: WriteStatus,
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

    fun getRemainingDate(): Int {
        return LocalDate.now().until(endedAt.toLocalDate(), ChronoUnit.DAYS).toInt()
    }

    fun changeWriteStatus() : JobDescription {
        return when(writeStatus) {
            WriteStatus.NOT_APPLIED -> copy(writeStatus = WriteStatus.WRITING)
            WriteStatus.WRITING -> copy(writeStatus = WriteStatus.WRITTEN)
            WriteStatus.WRITTEN -> copy(writeStatus = WriteStatus.WRITING)
            else -> throw JobDescriptionException.ModifyWriteStatusFailed()
        }
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
                writeStatus = WriteStatus.NOT_APPLIED,
                content = content,
                link = link,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
                startedAt = startedAt,
                endedAt = endedAt,
                userId = userId
            )
        }
    }

}
