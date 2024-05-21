package com.bamyanggang.persistence.experience.jpa.repository;

import com.bamyanggang.persistence.experience.jpa.entity.ExperienceJpaEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExperienceJpaRepository extends JpaRepository<ExperienceJpaEntity, UUID> {
    List<ExperienceJpaEntity> findAllByUserId(UUID userId);
    List<ExperienceJpaEntity> findByUserIdAndCreatedAtBetweenOrderByCreatedAtDesc(UUID userId, LocalDateTime startYear, LocalDateTime endYear);
    List<ExperienceJpaEntity> findByParentTagIdAndCreatedAtBetweenOrderByCreatedAtDesc(UUID parentTagId, LocalDateTime startYear, LocalDateTime endYear);
    List<ExperienceJpaEntity> findByChildTagIdAndCreatedAtBetweenOrderByCreatedAtDesc(UUID childTagId, LocalDateTime startYear, LocalDateTime endYear);
    List<ExperienceJpaEntity> findByTitleContaining(String title);

    @Modifying
    @Query("select e from ExperienceTagJpaEntity e where e.experienceId in :experienceIds")
    List<ExperienceJpaEntity> findByIds(@Param("experienceIds") List<UUID> experienceIds);
}
