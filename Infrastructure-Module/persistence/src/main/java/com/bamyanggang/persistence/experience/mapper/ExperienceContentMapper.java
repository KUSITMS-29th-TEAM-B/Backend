package com.bamyanggang.persistence.experience.mapper;

import com.bamyanggang.domainmodule.domain.experience.aggregate.ExperienceContent;
import com.bamyanggang.persistence.experience.jpa.entity.ExperienceContentJpaEntity;
import org.springframework.stereotype.Component;

@Component
class ExperienceContentMapper {
    public ExperienceContentJpaEntity toJpaEntity(ExperienceContent experienceContent) {
        return ExperienceContentJpaEntity.of(
                experienceContent.getId(),
                experienceContent.getQuestion(),
                experienceContent.getAnswer(),
                experienceContent.getExperienceId());
    }

    public ExperienceContent toDomainEntity(ExperienceContentJpaEntity experienceContentJpaEntity) {
        return ExperienceContent.Companion.toDomain(
                experienceContentJpaEntity.getExperienceContentId(),
                experienceContentJpaEntity.getQuestion(),
                experienceContentJpaEntity.getAnswer(),
                experienceContentJpaEntity.getExperienceId());
    }
}