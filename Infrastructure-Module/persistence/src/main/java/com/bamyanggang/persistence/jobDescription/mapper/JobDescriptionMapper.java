package com.bamyanggang.persistence.jobDescription.mapper;

import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.JobDescription;
import com.bamyanggang.persistence.jobDescription.jpa.entity.JobDescriptionJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class JobDescriptionMapper {

    public JobDescriptionJpaEntity toJpaEntity(JobDescription jobDescription) {
        return new JobDescriptionJpaEntity(
                jobDescription.getId(),
                jobDescription.getEnterpriseName(),
                jobDescription.getTitle(),
                jobDescription.getContent(),
                jobDescription.getLink(),
                jobDescription.getCreatedAt(),
                jobDescription.getUpdatedAt(),
                jobDescription.getStartedAt(),
                jobDescription.getEndedAt(),
                jobDescription.getUserId()
        );
    }


    public JobDescription toDomainEntity(JobDescriptionJpaEntity jobDescriptionJpaEntity) {
        return JobDescription.Companion.toDomain(
                jobDescriptionJpaEntity.getJobDescriptionId(),
                jobDescriptionJpaEntity.getEnterpriseName(),
                jobDescriptionJpaEntity.getTitle(),
                jobDescriptionJpaEntity.getContent(),
                jobDescriptionJpaEntity.getLink(),
                jobDescriptionJpaEntity.getCreatedAt(),
                jobDescriptionJpaEntity.getUpdatedAt(),
                jobDescriptionJpaEntity.getStartedAt(),
                jobDescriptionJpaEntity.getEndedAt(),
                jobDescriptionJpaEntity.getUserId()
        );
    }
}
