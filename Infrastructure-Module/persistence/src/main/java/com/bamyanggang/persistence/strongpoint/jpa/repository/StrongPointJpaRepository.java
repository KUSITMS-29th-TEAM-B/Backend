package com.bamyanggang.persistence.strongpoint.jpa.repository;

import com.bamyanggang.persistence.strongpoint.jpa.entity.StrongPointJpaEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StrongPointJpaRepository extends JpaRepository<StrongPointJpaEntity, UUID> {
    List<StrongPointJpaEntity> findAllByUserId(UUID userId);
}
