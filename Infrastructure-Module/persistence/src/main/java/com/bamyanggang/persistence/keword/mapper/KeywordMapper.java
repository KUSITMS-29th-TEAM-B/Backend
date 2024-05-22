package com.bamyanggang.persistence.keword.mapper;

import com.bamyanggang.domainmodule.domain.keyword.aggregate.Keyword;
import com.bamyanggang.persistence.keword.jpa.entity.KeywordJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class KeywordMapper {
    public KeywordJpaEntity toJpaEntity(Keyword keyword) {
        return new KeywordJpaEntity(
                keyword.getId(),
                keyword.getDefaultKeywordId(),
                keyword.getUserId()
        );
    }

    public Keyword toDomainEntity(KeywordJpaEntity keywordJpaEntity) {
        return new Keyword(
                keywordJpaEntity.getKeywordId(),
                keywordJpaEntity.getDefaultKeywordId(),
                keywordJpaEntity.getUserId()
        );
    }
}
