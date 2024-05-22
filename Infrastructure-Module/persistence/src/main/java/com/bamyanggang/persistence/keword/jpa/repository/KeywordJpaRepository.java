package com.bamyanggang.persistence.keword.jpa.repository;

import com.bamyanggang.persistence.keword.jpa.entity.KeywordJpaEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordJpaRepository extends JpaRepository<KeywordJpaEntity, UUID> {
}
