package com.bamyanggang.persistence.jobDescription.jpa.repository;

import com.bamyanggang.persistence.jobDescription.jpa.entity.ApplyJpaEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyJpaRepository extends JpaRepository<ApplyJpaEntity, UUID> {

    Optional<ApplyJpaEntity> findByJobDescriptionId(UUID jobDescriptionId);

    boolean existsByJobDescriptionId(UUID jobDescriptionId);

}
