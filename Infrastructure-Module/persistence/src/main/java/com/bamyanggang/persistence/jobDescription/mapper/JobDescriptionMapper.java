package com.bamyanggang.persistence.jobDescription.mapper;

import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.Apply;
import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.ApplyContent;
import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.JobDescription;
import com.bamyanggang.persistence.jobDescription.jpa.entity.ApplyContentJpaEntity;
import com.bamyanggang.persistence.jobDescription.jpa.entity.ApplyJpaEntity;
import com.bamyanggang.persistence.jobDescription.jpa.entity.JobDescriptionJpaEntity;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class JobDescriptionMapper {

    public JobDescriptionJpaEntity toJpaEntity(JobDescription jobDescription) {
        return new JobDescriptionJpaEntity(
                jobDescription.getId(),
                jobDescription.getEnterpriseName(),
                jobDescription.getTitle(),
                jobDescription.getWriteStatus(),
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
        return new JobDescription(
                jobDescriptionJpaEntity.getJobDescriptionId(),
                jobDescriptionJpaEntity.getEnterpriseName(),
                jobDescriptionJpaEntity.getTitle(),
                jobDescriptionJpaEntity.getWriteStatus(),
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
        List<ApplyContentJpaEntity> applyContentJpaEntities = apply.getContents().stream()
                .map(this::toApplyContentJpaEntity).toList();

        return new ApplyJpaEntity(
                apply.getId(),
                apply.getCreatedAt(),
                apply.getUpdatedAt(),
                applyContentJpaEntities,
                apply.getJobDescriptionId()
        );
    }

    public Apply toApplyDomainEntity(ApplyJpaEntity applyJpaEntity) {
        List<ApplyContent> applyContents = applyJpaEntity.getApplyContents().stream()
                .map(this::toApplyContentDomainEntity).toList();

        return new Apply(
                applyJpaEntity.getApplyId(),
                applyJpaEntity.getCreatedAt(),
                applyJpaEntity.getUpdatedAt(),
                applyContents,
                applyJpaEntity.getJobDescriptionId()
        );
    }

    public ApplyContentJpaEntity toApplyContentJpaEntity(ApplyContent applyContent) {
        return new ApplyContentJpaEntity(
                applyContent.getId(),
                applyContent.getQuestion(),
                applyContent.getAnswer()
        );
    }

    public ApplyContent toApplyContentDomainEntity(ApplyContentJpaEntity applyContentJpaEntity) {
        return new ApplyContent(
                applyContentJpaEntity.getApplyContentId(),
                applyContentJpaEntity.getQuestion(),
                applyContentJpaEntity.getAnswer()
        );
    }

}
