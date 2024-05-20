package com.bamyanggang.persistence.bookmark.jpa.repository;

import com.bamyanggang.domainmodule.domain.bookmark.enums.BookmarkStatus;
import com.bamyanggang.persistence.bookmark.jpa.entity.BookmarkJpaEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkJpaRepository extends JpaRepository<BookmarkJpaEntity, UUID> {

    Optional<BookmarkJpaEntity> findByJobDescriptionIdAndExperienceId(UUID jobDescriptionId, UUID experienceId);
    List<BookmarkJpaEntity> findByBookmarkStatusAndJobDescriptionId(BookmarkStatus status, UUID jobDescriptionId);
}
