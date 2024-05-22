package com.bamyanggang.persistence.keword;

import com.bamyanggang.domainmodule.domain.keyword.repository.KeywordRepository;
import com.bamyanggang.persistence.keword.jpa.repository.KeywordJpaRepository;
import com.bamyanggang.persistence.keword.mapper.KeywordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class KeywordRepositoryImpl implements KeywordRepository {
    private final KeywordJpaRepository keywordJpaRepository;
    private final KeywordMapper keywordMapper;
}
