package com.bamyanggang.persistence.experience.jpa.repository;

import com.bamyanggang.persistence.experience.jpa.entity.ExperienceJpaEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceJpaRepository extends JpaRepository<ExperienceJpaEntity, UUID> {
}
