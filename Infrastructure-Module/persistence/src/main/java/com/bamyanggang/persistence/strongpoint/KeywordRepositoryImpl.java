package com.bamyanggang.persistence.strongpoint;

import com.bamyanggang.domainmodule.domain.strongpoint.repository.KeywordRepository;
import com.bamyanggang.persistence.strongpoint.jpa.repository.KeywordJpaRepository;
import com.bamyanggang.persistence.strongpoint.mapper.KeywordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class KeywordRepositoryImpl implements KeywordRepository {
    private final KeywordJpaRepository keywordJpaRepository;
    private final KeywordMapper keywordMapper;
}
