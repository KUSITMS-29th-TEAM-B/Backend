package com.bamyanggang.persistence.experience.mapper

import com.bamyanggang.domainmodule.domain.experience.aggregate.Experience
import com.bamyanggang.persistence.experience.jpa.entity.ExperienceJpaEntity
import com.bamyanggang.persistence.experience.jpa.entity.TagJpaEntity
import com.bamyanggang.persistence.experience.jpa.repository.ExperienceContentJpaRepository
import com.bamyanggang.persistence.experience.jpa.repository.ExperienceTagJpaRepository
import com.bamyanggang.persistence.strongpoint.jpa.repository.StrongPointJpaRepository
import com.bamyanggang.persistence.strongpoint.mapper.StrongPointMapper
import org.springframework.stereotype.Component

@Component
class ExperienceMapper(
    private val experienceTagJpaRepository: ExperienceTagJpaRepository,
    private val strongPointJpaRepository: StrongPointJpaRepository,
    private val experienceContentJpaRepository: ExperienceContentJpaRepository,

    private val tagMapper: TagMapper,
    private val strongPointMapper: StrongPointMapper,
    private val experienceContentMapper: ExperienceContentMapper,
) {
    fun toJpaEntity(experience: Experience) =
        ExperienceJpaEntity.of(
            experience.id,
            experience.title,
            experience.startedAt,
            experience.endedAt,
            experience.userId,
            experience.createdAt,
            experience.updatedAt,
        )


    fun toDomainEntity(experienceJpaEntity: ExperienceJpaEntity) : Experience{
        val experienceTagJpaEntity = experienceTagJpaRepository.findByExperienceId(experienceJpaEntity.id)
        val (parentTagJpaEntity, childTagJpaEntity) = CompareTag(experienceTagJpaEntity[0].tag, experienceTagJpaEntity[1].tag)

        Experience.toDomain(
            experienceJpaEntity.id,
            experienceJpaEntity.userId,
            tagMapper.toDomain(parentTagJpaEntity),
            tagMapper.toDomain(childTagJpaEntity),
            strongPointMapper.toDomain(),
            experienceJpaEntity.title,
            experienceContentMapper.toDomain(),
            experienceJpaEntity.startedAt,
            experienceJpaEntity.endedAt,
            experienceJpaEntity.createdAt,
            experienceJpaEntity.updatedAt,
        )
    }
}

object CompareTag {
    operator fun invoke(tag1: TagJpaEntity, tag2: TagJpaEntity): Pair<TagJpaEntity, TagJpaEntity> =
        when {
            tag1.parentTag == null -> Pair(tag1, tag2)
            tag2.parentTag == null -> Pair(tag2, tag1)
            else -> Pair(tag1, tag2)
        }
}


