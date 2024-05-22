package com.bamyanggang.persistence.strongpoint.mapper;

import com.bamyanggang.domainmodule.domain.strongpoint.aggregate.Keyword;
import com.bamyanggang.persistence.strongpoint.jpa.entity.KeywordJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class KeywordMapper {
    public KeywordJpaEntity toJpaEntity(Keyword keyword) {
        return new KeywordJpaEntity(
                keyword.getId(),
                keyword.getDefaultKeywordId()
        );
    }

    public Keyword toDomainEntity(KeywordJpaEntity keywordJpaEntity) {
        return new Keyword(
                keywordJpaEntity.getKeywordId(),
                keywordJpaEntity.getDefaultKeywordId()
        );
    }
}
