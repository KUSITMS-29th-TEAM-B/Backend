package com.bamyanggang.persistence.jobDescription.mapper;

import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.Apply;
import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.ApplyContent;
import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.JobDescription;
import com.bamyanggang.persistence.jobDescription.jpa.entity.ApplyContentJpaEntity;
import com.bamyanggang.persistence.jobDescription.jpa.entity.ApplyJpaEntity;
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

    public ApplyJpaEntity toApplyJpaEntity(Apply apply) {
        return new ApplyJpaEntity(
                apply.getId(),
                apply.getWriteStatus(),
                apply.getCreatedAt(),
                apply.getUpdatedAt(),
                apply.getJobDescriptionId()
        );
    }

    public Apply toApplyDomainEntity(ApplyJpaEntity applyJpaEntity) {
        return Apply.Companion.toDomain(
                applyJpaEntity.getApplyId(),
                applyJpaEntity.getWriteStatus(),
                applyJpaEntity.getCreatedAt(),
                applyJpaEntity.getUpdatedAt(),
                applyJpaEntity.getJobDescriptionId()
        );
    }

    public ApplyContentJpaEntity toApplyContentJpaEntity(ApplyContent applyContent) {
        return new ApplyContentJpaEntity(
                applyContent.getId(),
                applyContent.getQuestion(),
                applyContent.getAnswer(),
                applyContent.getApplyId()
        );
    }

    public ApplyContent toApplyContentDomainEntity(ApplyContentJpaEntity applyContentJpaEntity) {
        return ApplyContent.Companion.toDomain(
                applyContentJpaEntity.getApplyContentId(),
                applyContentJpaEntity.getQuestion(),
                applyContentJpaEntity.getAnswer(),
                applyContentJpaEntity.getApplyId()
        );
    }

}
