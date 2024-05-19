package com.bamyanggang.persistence.experience.mapper;

import com.bamyanggang.domainmodule.domain.experience.aggregate.Experience;
import com.bamyanggang.domainmodule.domain.experience.aggregate.ExperienceContent;
import com.bamyanggang.domainmodule.domain.experience.aggregate.ExperienceStrongPoint;
import com.bamyanggang.persistence.experience.jpa.entity.ExperienceContentJpaEntity;
import com.bamyanggang.persistence.experience.jpa.entity.ExperienceJpaEntity;
import com.bamyanggang.persistence.experience.jpa.entity.ExperienceStrongPointJpaEntity;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ExperienceMapper {
    public ExperienceJpaEntity toExperienceJpaEntity(Experience experience) {
        List<ExperienceContentJpaEntity> experienceContentJpaEntities = experience.getContents().stream()
                .map(this::toExperienceContentJpaEntity).toList();

        List<ExperienceStrongPointJpaEntity> strongPointInfoJpaEntities = experience.getExperienceStrongPoints().stream()
                .map(this::toStrongPointInfoJpaEntity).toList();

        return new ExperienceJpaEntity(
                experience.getId(),
                experience.getTitle(),
                experience.getParentTagId(),
                experience.getChildTagId(),
                experienceContentJpaEntities,
                strongPointInfoJpaEntities,
                experience.getStartedAt(),
                experience.getEndedAt(),
                experience.getUserId(),
                experience.getCreatedAt(),
                experience.getUpdatedAt()
        );
    }

    public Experience toExperienceDomainEntity(ExperienceJpaEntity experienceJpaEntity) {
        List<ExperienceContent> contents = experienceJpaEntity.getContents().stream()
                .map(this::toExperienceContentDomainEntity).toList();

        List<ExperienceStrongPoint> experienceStrongPoints = experienceJpaEntity.getStrongPointInfos().stream()
                .map(this::toStrongPointInfoDomainEntity).toList();
        return Experience.Companion.toDomain(
                experienceJpaEntity.getExperienceId(),
                experienceJpaEntity.getUserId(),
                experienceJpaEntity.getTitle(),
                experienceJpaEntity.getParentTagId(),
                experienceJpaEntity.getChildTagId(),
                contents,
                experienceStrongPoints,
                experienceJpaEntity.getStartedAt(),
                experienceJpaEntity.getEndedAt(),
                experienceJpaEntity.getCreatedAt(),
                experienceJpaEntity.getUpdatedAt()
        );
    }

    public ExperienceContentJpaEntity toExperienceContentJpaEntity(ExperienceContent experienceContent) {
        return new ExperienceContentJpaEntity(
                experienceContent.getId(),
                experienceContent.getQuestion(),
                experienceContent.getAnswer()
        );
    }

    public ExperienceContent toExperienceContentDomainEntity(ExperienceContentJpaEntity experienceContentJpaEntity) {
        return ExperienceContent.Companion.toDomain(
                experienceContentJpaEntity.getExperienceContentId(),
                experienceContentJpaEntity.getQuestion(),
                experienceContentJpaEntity.getAnswer()
        );
    }

    public ExperienceStrongPointJpaEntity toStrongPointInfoJpaEntity(ExperienceStrongPoint experienceStrongPoint) {
        return new ExperienceStrongPointJpaEntity(experienceStrongPoint.getId(), experienceStrongPoint.getStrongPointId());
    }

    public ExperienceStrongPoint toStrongPointInfoDomainEntity(ExperienceStrongPointJpaEntity experienceStrongPointJpaEntity) {
        return ExperienceStrongPoint.Companion.toDomain(
                experienceStrongPointJpaEntity.getExperienceStrongPointId(),
                experienceStrongPointJpaEntity.getStrongPointId()
        );
    }
}
