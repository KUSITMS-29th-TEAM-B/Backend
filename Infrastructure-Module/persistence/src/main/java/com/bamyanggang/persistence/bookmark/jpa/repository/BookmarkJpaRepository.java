package com.bamyanggang.persistence.bookmark.jpa.repository;

import com.bamyanggang.persistence.bookmark.jpa.entity.BookmarkJpaEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkJpaRepository extends JpaRepository<BookmarkJpaEntity, UUID> {

    Optional<BookmarkJpaEntity> findByJobDescriptionIdAndExperienceId(UUID jobDescriptionId, UUID experienceId);

}
