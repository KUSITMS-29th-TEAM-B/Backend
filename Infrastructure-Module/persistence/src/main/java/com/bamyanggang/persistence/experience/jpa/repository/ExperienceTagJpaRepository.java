package com.bamyanggang.persistence.experience.jpa.repository;

import com.bamyanggang.persistence.experience.jpa.entity.ExperienceTagJpaEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceTagJpaRepository extends JpaRepository<ExperienceTagJpaEntity, UUID> {
    List<ExperienceTagJpaEntity> findByExperienceId(UUID experienceId);
}
