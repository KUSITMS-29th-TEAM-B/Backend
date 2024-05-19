package com.bamyanggang.domainmodule.domain.jobDescription.aggregate

import com.bamyanggang.domainmodule.common.entity.DomainEntity
import com.bamyanggang.domainmodule.domain.experience.aggregate.ExperienceContent
import com.bamyanggang.domainmodule.domain.jobDescription.enums.WriteStatus
import com.example.uuid.UuidCreator
import java.time.LocalDateTime
import java.util.UUID

data class Apply(
    override val id : UUID = UuidCreator.create(),
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val contents: List<ApplyContent>,
    val jobDescriptionId: UUID,
    ): DomainEntity {

    companion object {
        fun create(
            contents: List<ApplyContent>,
            jobDescriptionId: UUID
        ): Apply {
            return Apply(
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
                contents = contents,
                jobDescriptionId = jobDescriptionId,
            )
        }
    }

}
