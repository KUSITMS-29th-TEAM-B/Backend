package com.bamyanggang.persistence.experience.mapper

import com.bamyanggang.domainmodule.domain.experience.aggregate.ExperienceContent
import com.bamyanggang.persistence.experience.jpa.entity.ExperienceContentJpaEntity
import org.springframework.stereotype.Component

@Component
class ExperienceContentMapper(
) {
    fun toJpaEntity(experienceContent: ExperienceContent) : ExperienceContentJpaEntity =
        ExperienceContentJpaEntity.of(
            experienceContent.id,
            experienceContent.question,
            experienceContent.answer,
            experienceContent.experienceId
        )

    fun toDomain(experienceContentJpaEntity: ExperienceContentJpaEntity): ExperienceContent =
        ExperienceContent.toDomain(
            experienceContentJpaEntity.id,
            experienceContentJpaEntity.question,
            experienceContentJpaEntity.answer,
            experienceContentJpaEntity.experienceId
        )
}