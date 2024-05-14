package com.bamyanggang.persistence.experience.mapper

import com.bamyanggang.domainmodule.domain.experience.aggregate.Experience
import com.bamyanggang.persistence.experience.jpa.entity.ExperienceJpaEntity
import org.springframework.stereotype.Component
import java.util.*

@Component
class ExperienceMapper(
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

    fun toDomainEntity(
        experienceJpaEntity: ExperienceJpaEntity,
        parentTagId: UUID,
        childTagId: UUID,
        strongPointIds: List<UUID>,
        experienceContentIds: List<UUID>,
    ): Experience =
        Experience.toDomain(
            experienceJpaEntity.id,
            experienceJpaEntity.userId,
            parentTagId,
            childTagId,
            strongPointIds,
            experienceJpaEntity.title,
            experienceContentIds,
            experienceJpaEntity.startedAt,
            experienceJpaEntity.endedAt,
            experienceJpaEntity.createdAt,
            experienceJpaEntity.updatedAt,
        )
}

//object CompareTag {
//    operator fun invoke(tag1: TagJpaEntity, tag2: TagJpaEntity): Pair<TagJpaEntity, TagJpaEntity> =
//        when {
//            tag1.parentTag == null -> Pair(tag1, tag2)
//            tag2.parentTag == null -> Pair(tag2, tag1)
//            else -> Pair(tag1, tag2)
//        }
//}


