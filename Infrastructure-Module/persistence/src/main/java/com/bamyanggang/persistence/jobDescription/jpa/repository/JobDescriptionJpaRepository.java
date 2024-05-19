package com.bamyanggang.persistence.jobDescription.jpa.repository;

import com.bamyanggang.persistence.jobDescription.jpa.entity.JobDescriptionJpaEntity;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobDescriptionJpaRepository extends JpaRepository<JobDescriptionJpaEntity, UUID> {

    Slice<JobDescriptionJpaEntity> findAllByUserId(UUID userId, Pageable pageable);

    Slice<JobDescriptionJpaEntity> findAllByUserIdOrderByCreatedAtDesc(UUID userId, Pageable pageable);


}
