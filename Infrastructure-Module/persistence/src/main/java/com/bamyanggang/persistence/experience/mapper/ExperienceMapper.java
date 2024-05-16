package com.bamyanggang.persistence.experience.mapper;

import com.bamyanggang.domainmodule.domain.experience.aggregate.Experience;
import com.bamyanggang.persistence.experience.jpa.entity.ExperienceJpaEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class ExperienceMapper {
    public ExperienceJpaEntity toJpaEntity(Experience experience) {
        return ExperienceJpaEntity.of(
                experience.getId(),
                experience.getTitle(),
                experience.getStartedAt(),
                experience.getEndedAt(),
                experience.getUserId(),
                experience.getCreatedAt(),
                experience.getUpdatedAt()
        );
    }

    public Experience toDomainEntity(ExperienceJpaEntity experienceJpaEntity, UUID parentTagId, UUID childTagId,
                                     List<UUID> strongPointIds, List<UUID> experienceContentIds) {
        return Experience.Companion.toDomain(
                experienceJpaEntity.getExperienceId(),
                experienceJpaEntity.getUserId(),
                parentTagId,
                childTagId,
                strongPointIds,
                experienceJpaEntity.getTitle(),
                experienceContentIds,
                experienceJpaEntity.getStartedAt(),
                experienceJpaEntity.getEndedAt(),
                experienceJpaEntity.getCreatedAt(),
                experienceJpaEntity.getUpdatedAt()
        );
    }
}
