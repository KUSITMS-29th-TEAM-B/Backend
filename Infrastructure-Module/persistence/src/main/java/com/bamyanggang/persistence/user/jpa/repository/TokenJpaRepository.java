package com.bamyanggang.persistence.user.jpa.repository;

import com.bamyanggang.persistence.user.jpa.entity.TokenJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenJpaRepository extends JpaRepository<TokenJpaEntity, Long> {
}
