package com.bamyanggang.domainmodule.domain.jobDescription.aggregate

import com.bamyanggang.domainmodule.common.entity.DomainEntity
import com.bamyanggang.domainmodule.domain.jobDescription.enums.WriteStatus
import com.example.uuid.UuidCreator
import java.time.LocalDateTime
import java.util.UUID

data class Apply(
    override val id : UUID = UuidCreator.create(),
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val jobDescriptionId: UUID,
    ): DomainEntity {

    companion object {
        fun create(
            jobDescriptionId: UUID
        ): Apply {
            return Apply(
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
                jobDescriptionId = jobDescriptionId
            )
        }

        fun toDomain(
            id: UUID,
            createdAt: LocalDateTime,
            updatedAt: LocalDateTime,
            jobDescriptionId: UUID
        ): Apply {
            return Apply(
                id = id,
                createdAt = createdAt,
                updatedAt = updatedAt,
                jobDescriptionId = jobDescriptionId
            )
        }
    }

}
