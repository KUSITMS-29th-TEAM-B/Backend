package com.bamyanggang.persistence.experience.jpa.repository;

import com.bamyanggang.persistence.experience.jpa.entity.ExperienceJpaEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceJpaRepository extends JpaRepository<ExperienceJpaEntity, UUID> {
    List<ExperienceJpaEntity> findAllByUserId(UUID userId);
    List<ExperienceJpaEntity> findByCreatedAtBetweenOrderByCreatedAtDesc(LocalDateTime startYear, LocalDateTime endYear);
}
