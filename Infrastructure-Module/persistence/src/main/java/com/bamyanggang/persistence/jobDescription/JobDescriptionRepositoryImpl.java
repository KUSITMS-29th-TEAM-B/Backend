package com.bamyanggang.persistence.jobDescription;

import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.JobDescription;
import com.bamyanggang.domainmodule.domain.jobDescription.repository.JobDescriptionRepository;
import com.bamyanggang.persistence.jobDescription.jpa.entity.JobDescriptionJpaEntity;
import com.bamyanggang.persistence.jobDescription.jpa.repository.JobDescriptionJpaRepository;
import com.bamyanggang.persistence.jobDescription.mapper.JobDescriptionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JobDescriptionRepositoryImpl implements JobDescriptionRepository {
    private final JobDescriptionJpaRepository jobDescriptionJpaRepository;
    private final JobDescriptionMapper jobDescriptionMapper;

    @Override
    public void save(JobDescription jobDescription) {
        JobDescriptionJpaEntity jobDescriptionJpaEntity = jobDescriptionMapper.toJpaEntity(jobDescription);
        jobDescriptionJpaRepository.save(jobDescriptionJpaEntity);
    }
}
