package com.bamyanggang.domainmodule.domain.experience.aggregate

import com.bamyanggang.domainmodule.common.entity.DomainEntity
import com.example.uuid.UuidCreator
import java.util.*

data class StrongPointInfo(
    override val id: UUID = UuidCreator.create(),
    val strongPointId: UUID
) : DomainEntity {

    companion object {
        fun create(strongPointId: UUID): StrongPointInfo {
            return StrongPointInfo(strongPointId = strongPointId)
        }

        fun toDomain(id: UUID, strongPointId: UUID): StrongPointInfo {
            return StrongPointInfo(id, strongPointId)
        }
    }
}
