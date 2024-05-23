package com.bamyanggang.persistence.jobDescription;

import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.Apply;
import com.bamyanggang.domainmodule.domain.jobDescription.repository.ApplyRepository;
import com.bamyanggang.persistence.common.exception.PersistenceException;
import com.bamyanggang.persistence.jobDescription.jpa.entity.ApplyJpaEntity;
import com.bamyanggang.persistence.jobDescription.jpa.repository.ApplyJpaRepository;
import com.bamyanggang.persistence.jobDescription.mapper.JobDescriptionMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
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

    @Override
    public Apply findByJobDescriptionId(UUID jobDescriptionId) {
        return applyJpaRepository.findByJobDescriptionId(jobDescriptionId)
                .map(jobDescriptionMapper::toApplyDomainEntity)
                .orElseThrow(() -> new PersistenceException.NotFound());
    }

    @Override
    public boolean existsByJobDescriptionId(UUID jobDescriptionId) {
        return applyJpaRepository.existsByJobDescriptionId(jobDescriptionId);
    }

}
