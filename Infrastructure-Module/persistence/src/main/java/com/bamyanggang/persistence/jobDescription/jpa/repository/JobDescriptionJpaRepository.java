package com.bamyanggang.persistence.jobDescription.jpa.repository;

import com.bamyanggang.domainmodule.domain.jobDescription.aggregate.JobDescription;
import com.bamyanggang.domainmodule.domain.jobDescription.enums.WriteStatus;
import com.bamyanggang.persistence.jobDescription.jpa.entity.JobDescriptionJpaEntity;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JobDescriptionJpaRepository extends JpaRepository<JobDescriptionJpaEntity, UUID> {

    Slice<JobDescriptionJpaEntity> findAllByUserId(UUID userId, Pageable pageable);

    Slice<JobDescriptionJpaEntity> findAllByUserIdOrderByCreatedAtDesc(UUID userId, Pageable pageable);

    @Query(
            """
            SELECT jd FROM JobDescriptionJpaEntity jd
            LEFT JOIN ApplyJpaEntity a ON jd.jobDescriptionId = a.jobDescriptionId
            WHERE jd.userId = :userId AND a.writeStatus = :writeStatus
            ORDER BY jd.createdAt DESC
            """
    )
    Slice<JobDescriptionJpaEntity> findAllByUserIdAndOrderByCreatedAtDescWithApplyWithWriteStatus(UUID userId, WriteStatus writeStatus, Pageable pageable);

    @Query(
            """
            SELECT jd FROM JobDescriptionJpaEntity jd
            LEFT JOIN ApplyJpaEntity a ON jd.jobDescriptionId = a.jobDescriptionId
            WHERE jd.userId = :userId
            """
    )
    Slice<JobDescriptionJpaEntity> findAllByUserIdWithApply(UUID userId, Pageable pageable);

    @Query(
            """
            SELECT jd FROM JobDescriptionJpaEntity jd
            LEFT JOIN ApplyJpaEntity a ON jd.jobDescriptionId = a.jobDescriptionId
            WHERE jd.userId = :userId AND a.writeStatus = :writeStatus
            """
    )
    Slice<JobDescriptionJpaEntity> findAllByUserIdWithApplyAndWriteStatus(UUID userId, WriteStatus writeStatus, Pageable pageable);

    @Query(
            """
            SELECT jd FROM JobDescriptionJpaEntity jd
            LEFT JOIN ApplyJpaEntity a ON jd.jobDescriptionId = a.jobDescriptionId
            WHERE jd.userId = :userId
            ORDER BY jd.createdAt DESC
            """
    )
    Slice<JobDescriptionJpaEntity> findAllByUserIdAndOrderByCreatedAtDescWithApply(UUID userId, Pageable pageable);

}
