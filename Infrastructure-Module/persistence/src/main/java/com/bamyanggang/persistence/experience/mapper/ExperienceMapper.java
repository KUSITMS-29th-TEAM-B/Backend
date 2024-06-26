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

        List<ExperienceStrongPointJpaEntity> strongPointInfoJpaEntities = experience.getStrongPoints().stream()
                .map(this::toExperienceStrongPointJpaEntity).toList();

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
                .map(this::toExperienceStrongPointDomainEntity).toList();
        return new Experience(
                experienceJpaEntity.getExperienceId(),
                experienceJpaEntity.getTitle(),
                experienceJpaEntity.getUserId(),
                experienceJpaEntity.getParentTagId(),
                experienceJpaEntity.getChildTagId(),
                experienceStrongPoints,
                contents,
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
        return new ExperienceContent(
                experienceContentJpaEntity.getExperienceContentId(),
                experienceContentJpaEntity.getQuestion(),
                experienceContentJpaEntity.getAnswer()
        );
    }

    public ExperienceStrongPointJpaEntity toExperienceStrongPointJpaEntity(ExperienceStrongPoint experienceStrongPoint) {
        return new ExperienceStrongPointJpaEntity(experienceStrongPoint.getId(), experienceStrongPoint.getStrongPointId());
    }

    public ExperienceStrongPoint toExperienceStrongPointDomainEntity(ExperienceStrongPointJpaEntity experienceStrongPointJpaEntity) {
        return new ExperienceStrongPoint(
                experienceStrongPointJpaEntity.getExperienceStrongPointId(),
                experienceStrongPointJpaEntity.getStrongPointId()
        );
    }
}
