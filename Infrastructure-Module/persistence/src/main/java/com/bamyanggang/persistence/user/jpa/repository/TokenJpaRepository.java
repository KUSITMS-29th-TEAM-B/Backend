package com.bamyanggang.persistence.user.jpa.repository;

import com.bamyanggang.persistence.user.jpa.entity.TokenJpaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenJpaRepository extends JpaRepository<TokenJpaEntity, Long> {
    Optional<TokenJpaEntity> findByValue(String value);

}
