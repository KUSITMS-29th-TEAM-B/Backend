package com.bamyanggang.domainmodule.domain.jobDescription.aggregate

import com.bamyanggang.domainmodule.common.entity.DomainEntity
import com.bamyanggang.domainmodule.domain.jobDescription.enums.WriteStatus
import com.example.uuid.UuidCreator
import java.time.LocalDateTime
import java.util.UUID

data class Apply(
    override val id : UUID = UuidCreator.create(),
    val title : String,
    val writeStatus: WriteStatus = WriteStatus.WRITING,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val jobDescriptionId: UUID,
    ): DomainEntity {

    init {
        require(title.isNotBlank()) { "제목은 필수입니다." }
    }

    companion object {
        fun create(
            title: String,
            jobDescriptionId: UUID
        ): Apply {
            return Apply(
                title = title,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
                jobDescriptionId = jobDescriptionId
            )
        }

        fun toDomain(
            id: UUID,
            title: String,
            writeStatus: WriteStatus,
            createdAt: LocalDateTime,
            updatedAt: LocalDateTime,
            jobDescriptionId: UUID
        ): Apply {
            return Apply(
                id = id,
                title = title,
                writeStatus = writeStatus,
                createdAt = createdAt,
                updatedAt = updatedAt,
                jobDescriptionId = jobDescriptionId
            )
        }
    }

}
