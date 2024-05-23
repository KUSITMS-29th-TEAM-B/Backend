package com.bamyanggang.persistence.tag.mapper;

import com.bamyanggang.domainmodule.domain.tag.aggregate.Tag;
import com.bamyanggang.persistence.tag.jpa.entity.TagJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {
    public TagJpaEntity toJpaEntity(Tag tag) {
        return new TagJpaEntity(tag.getId(), tag.getName(), tag.getParentTagId(), tag.getUserId());
    }

    public Tag toDomainEntity(TagJpaEntity tagJpaEntity) {
        return new Tag(tagJpaEntity.getTagId(),
                tagJpaEntity.getName(),
                tagJpaEntity.getParentTagId(),
                tagJpaEntity.getUserId()
        );
    }
}
