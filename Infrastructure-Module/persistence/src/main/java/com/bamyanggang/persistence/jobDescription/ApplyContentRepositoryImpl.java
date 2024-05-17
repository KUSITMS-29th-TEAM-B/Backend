package com.bamyanggang.persistence.jobDescription;

import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.ApplyContent;
import com.bamyanggang.domainmodule.domain.jobDescription.repository.ApplyContentRepository;
import com.bamyanggang.persistence.jobDescription.jpa.entity.ApplyContentJpaEntity;
import com.bamyanggang.persistence.jobDescription.jpa.repository.ApplyContentJpaRepository;
import com.bamyanggang.persistence.jobDescription.mapper.JobDescriptionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ApplyContentRepositoryImpl implements ApplyContentRepository {
    private final ApplyContentJpaRepository applyContentJpaRepository;
    private final JobDescriptionMapper jobDescriptionMapper;

    @Override
    public void save(ApplyContent applyContent) {
        ApplyContentJpaEntity applyContentJpaEntity = jobDescriptionMapper.toApplyContentJpaEntity(applyContent);
        applyContentJpaRepository.save(applyContentJpaEntity);
    }

}
