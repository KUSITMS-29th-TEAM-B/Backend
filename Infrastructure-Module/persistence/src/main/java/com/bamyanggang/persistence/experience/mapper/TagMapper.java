package com.bamyanggang.persistence.experience.mapper;

import com.bamyanggang.domainmodule.domain.experience.aggregate.Tag;
import com.bamyanggang.persistence.experience.jpa.entity.TagJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {
    public TagJpaEntity toJpaEntity(Tag tag) {
        return TagJpaEntity.of(tag.getId(), tag.getName(), tag.getParentTagId(), tag.getUserId());
    }

    public Tag toDomainEntity(TagJpaEntity tagJpaEntity) {
        return Tag.Companion.toDomain(tagJpaEntity.getId(),
                tagJpaEntity.getName(),
                tagJpaEntity.getParentTagId(),
                tagJpaEntity.getUserId()
        );
    }
}
