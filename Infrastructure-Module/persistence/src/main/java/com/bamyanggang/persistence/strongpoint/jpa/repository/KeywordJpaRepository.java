package com.bamyanggang.persistence.strongpoint.jpa.repository;

import com.bamyanggang.persistence.strongpoint.jpa.entity.KeywordJpaEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordJpaRepository extends JpaRepository<KeywordJpaEntity, UUID> {
}
