package com.bamyanggang.persistence.bookmark.jpa.repository;

import com.bamyanggang.domainmodule.domain.bookmark.enums.BookmarkStatus;
import com.bamyanggang.persistence.bookmark.jpa.entity.BookmarkJpaEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookmarkJpaRepository extends JpaRepository<BookmarkJpaEntity, UUID> {

    Optional<BookmarkJpaEntity> findByJobDescriptionIdAndExperienceId(UUID jobDescriptionId, UUID experienceId);

    @Query("select bm from BookmarkJpaEntity bm where bm.bookmarkStatus = :status and bm.jobDescriptionId = :jobDescriptionId")
    List<BookmarkJpaEntity> findByBookmarkStatusAndJobDescriptionId(@Param("status") BookmarkStatus status,
                                                                    @Param("jobDescriptionId") UUID jobDescriptionId);

    @Modifying
    @Query("select bm from BookmarkJpaEntity bm where bm.experienceId in :experienceIds and bm.bookmarkStatus = :status")
    List<BookmarkJpaEntity> findByBookmarkStatusAndExperienceIds(
            @Param("experienceIds") List<UUID> experienceIds,
            @Param("status") BookmarkStatus status);
}
