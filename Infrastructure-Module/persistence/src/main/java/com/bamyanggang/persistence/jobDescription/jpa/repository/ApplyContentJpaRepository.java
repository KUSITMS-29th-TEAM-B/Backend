package com.bamyanggang.persistence.jobDescription.jpa.repository;

import com.bamyanggang.persistence.jobDescription.jpa.entity.ApplyContentJpaEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyContentJpaRepository extends JpaRepository<ApplyContentJpaEntity, UUID> {
}
