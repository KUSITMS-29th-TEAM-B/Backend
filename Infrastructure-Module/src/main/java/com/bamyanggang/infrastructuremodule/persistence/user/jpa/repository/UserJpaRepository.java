package com.bamyanggang.infrastructuremodule.persistence.user.jpa.repository;

import com.bamyanggang.infrastructuremodule.persistence.user.jpa.entity.UserJpaEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, UUID> {
    UserJpaEntity findBySocialId(String socialId);

}
