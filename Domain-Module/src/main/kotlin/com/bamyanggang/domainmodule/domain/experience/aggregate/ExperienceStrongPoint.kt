package com.bamyanggang.domainmodule.domain.experience.aggregate

import com.bamyanggang.domainmodule.common.entity.DomainEntity
import com.example.uuid.UuidCreator
import java.util.*

data class ExperienceStrongPoint(
    override val id: UUID = UuidCreator.create(),
    val strongPointId: UUID
) : DomainEntity {

    companion object {
        fun create(strongPointId: UUID): ExperienceStrongPoint {
            return ExperienceStrongPoint(strongPointId = strongPointId)
        }
    }
}
