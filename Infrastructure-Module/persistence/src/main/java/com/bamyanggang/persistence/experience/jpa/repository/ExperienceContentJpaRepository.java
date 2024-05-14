package com.bamyanggang.persistence.experience.jpa.repository;

import com.bamyanggang.persistence.experience.jpa.entity.ExperienceContentJpaEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceContentJpaRepository extends JpaRepository<ExperienceContentJpaEntity, UUID> {
}
