package com.bamyanggang.persistence.experience.mapper

import com.bamyanggang.domainmodule.domain.experience.aggregate.StrongPoint
import com.bamyanggang.persistence.experience.jpa.entity.StrongPointJpaEntity
import org.springframework.stereotype.Component

@Component
class StrongPointMapper {
    fun toJpaEntity(strongPoint: StrongPoint) =
        StrongPointJpaEntity.of(
            strongPoint.id,
            strongPoint.name,
            strongPoint.userId
        )

    fun toDomain(strongPointJpaEntity: StrongPointJpaEntity) =
        StrongPoint.toDomain(
            strongPointJpaEntity.id,
            strongPointJpaEntity.name,
            strongPointJpaEntity.userId
        )
}