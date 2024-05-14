package com.bamyanggang.persistence.experience.mapper

import com.bamyanggang.domainmodule.domain.experience.aggregate.Tag
import com.bamyanggang.persistence.experience.jpa.entity.TagJpaEntity
import org.springframework.stereotype.Component

@Component
class TagMapper {
    fun toJpaEntity(tag: Tag): TagJpaEntity = TagJpaEntity.of(tag.id, tag.name, tag.parentTagId, tag.userId)

    fun toDomain(parentTagJpaEntity: TagJpaEntity): Tag =
        Tag.toDomain(parentTagJpaEntity.id,
            parentTagJpaEntity.name,
            parentTagJpaEntity.parentTagId,
            parentTagJpaEntity.userId
        )
}