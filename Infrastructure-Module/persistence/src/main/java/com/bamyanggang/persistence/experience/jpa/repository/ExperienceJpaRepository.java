package com.bamyanggang.persistence.experience.jpa.repository;

import com.bamyanggang.persistence.experience.jpa.entity.ExperienceJpaEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceJpaRepository extends JpaRepository<ExperienceJpaEntity, UUID> {
    List<ExperienceJpaEntity> findAllByUserId(UUID userId);
    List<ExperienceJpaEntity> findByUserIdAndCreatedAtBetweenOrderByCreatedAtDesc(UUID userId, LocalDateTime startYear, LocalDateTime endYear);
    List<ExperienceJpaEntity> findByParentTagIdAndCreatedAtBetweenOrderByCreatedAtDesc(UUID parentTagId, LocalDateTime startYear, LocalDateTime endYear);
    List<ExperienceJpaEntity> findByChildTagIdAndCreatedAtBetweenOrderByCreatedAtDesc(UUID childTagId, LocalDateTime startYear, LocalDateTime endYear);
    List<ExperienceJpaEntity> findByUserIdAndParentTagId(UUID userId, UUID parentTagId);
    List<ExperienceJpaEntity> findByUserIdAndParentTagIdAndCreatedAtBetweenOrderByCreatedAtDesc(
            UUID userId, UUID parentTagId, LocalDateTime startYear, LocalDateTime endYear);
}
