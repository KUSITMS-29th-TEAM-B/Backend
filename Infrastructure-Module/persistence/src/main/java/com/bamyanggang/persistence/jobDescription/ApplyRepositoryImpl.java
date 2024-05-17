package com.bamyanggang.persistence.jobDescription;

import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.Apply;
import com.bamyanggang.domainmodule.domain.jobDescription.repository.ApplyRepository;
import com.bamyanggang.persistence.jobDescription.jpa.entity.ApplyJpaEntity;
import com.bamyanggang.persistence.jobDescription.jpa.repository.ApplyJpaRepository;
import com.bamyanggang.persistence.jobDescription.mapper.JobDescriptionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ApplyRepositoryImpl implements ApplyRepository {
    private final ApplyJpaRepository applyJpaRepository;
    private final JobDescriptionMapper jobDescriptionMapper;

    @Override
    public void save(Apply apply) {
        ApplyJpaEntity applyJpaEntity = jobDescriptionMapper.toApplyJpaEntity(apply);
        applyJpaRepository.save(applyJpaEntity);
    }

}
