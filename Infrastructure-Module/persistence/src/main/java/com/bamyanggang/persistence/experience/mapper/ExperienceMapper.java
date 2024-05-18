package com.bamyanggang.persistence.experience.mapper;

import com.bamyanggang.domainmodule.domain.experience.aggregate.Experience;
import com.bamyanggang.domainmodule.domain.experience.aggregate.ExperienceContent;
import com.bamyanggang.persistence.experience.jpa.entity.ExperienceContentJpaEntity;
import com.bamyanggang.persistence.experience.jpa.entity.ExperienceJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class ExperienceMapper {
    public ExperienceJpaEntity toExperienceJpaEntity(Experience experience) {
        return new ExperienceJpaEntity(
                experience.getId(),
                experience.getTitle(),
                experience.getStartedAt(),
                experience.getEndedAt(),
                experience.getUserId(),
                experience.getCreatedAt(),
                experience.getUpdatedAt()
        );
    }

    public Experience toExperienceDomainEntity(ExperienceJpaEntity experienceJpaEntity) {
        return Experience.Companion.toDomain(
                experienceJpaEntity.getExperienceId(),
                experienceJpaEntity.getUserId(),
                experienceJpaEntity.getTitle(),
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
                experienceContent.getAnswer(),
                experienceContent.getExperienceId()
        );
    }

    public ExperienceContent toExperienceContentDomainEntity(ExperienceContentJpaEntity experienceContentJpaEntity) {
        return ExperienceContent.Companion.toDomain(
                experienceContentJpaEntity.getExperienceContentId(),
                experienceContentJpaEntity.getQuestion(),
                experienceContentJpaEntity.getAnswer(),
                experienceContentJpaEntity.getExperienceId()
        );
    }
}
