package com.bamyanggang.persistence.user.jpa.repository;

import com.bamyanggang.persistence.user.jpa.entity.ProfileImageJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileImageJpaRepository extends JpaRepository<ProfileImageJpaEntity, Long> {
}
