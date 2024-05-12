package com.bamyanggang.persistence.user.jpa.repository;

import com.bamyanggang.persistence.user.jpa.entity.UserJpaEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, UUID> {
    UserJpaEntity findBySocialId(String socialId);

}
