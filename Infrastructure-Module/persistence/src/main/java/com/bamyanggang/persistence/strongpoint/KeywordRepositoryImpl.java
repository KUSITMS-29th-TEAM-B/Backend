package com.bamyanggang.persistence.strongpoint;

import com.bamyanggang.domainmodule.domain.strongpoint.aggregate.Keyword;
import com.bamyanggang.domainmodule.domain.strongpoint.repository.KeywordRepository;
import com.bamyanggang.persistence.strongpoint.jpa.entity.KeywordJpaEntity;
import com.bamyanggang.persistence.strongpoint.jpa.repository.KeywordJpaRepository;
import com.bamyanggang.persistence.strongpoint.mapper.KeywordMapper;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class KeywordRepositoryImpl implements KeywordRepository {
    private final KeywordJpaRepository keywordJpaRepository;
    private final KeywordMapper keywordMapper;

    @Override
    public List<Keyword> findByIds(List<UUID> strongPointIds) {
        System.out.println(strongPointIds);
        List<KeywordJpaEntity> keywordJpaEntities = keywordJpaRepository.findByKeywordIds(strongPointIds);
        return keywordJpaEntities.stream().map(keywordMapper::toDomainEntity).toList();
    }
}
