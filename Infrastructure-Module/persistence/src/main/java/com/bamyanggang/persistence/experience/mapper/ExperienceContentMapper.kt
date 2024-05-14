package com.bamyanggang.persistence.experience.mapper

import com.bamyanggang.domainmodule.domain.experience.aggregate.ExperienceContent
import com.bamyanggang.persistence.experience.jpa.entity.ExperienceContentJpaEntity
import org.springframework.stereotype.Component

@Component
class ExperienceContentMapper(
    private val experienceMapper: ExperienceMapper
) {

    fun toDomain(experienceContentJpaEntity: ExperienceContentJpaEntity): ExperienceContent {
        return ExperienceContent(
            experienceContentJpaEntity.id,
            experienceContentJpaEntity.question,
            experienceContentJpaEntity.answer,
            experienceMapper.toDomainEntity(experienceContentJpaEntity.experience)
        )
    }

    fun toJpaEntity(experienceContent: ExperienceContent) : ExperienceContentJpaEntity {
        return ExperienceContentJpaEntity.of(
            experienceContent.id,
            experienceContent.question,
            experienceContent.answer,
            experienceMapper.toJpaEntity(experienceContent.experience)
        )
    }
}