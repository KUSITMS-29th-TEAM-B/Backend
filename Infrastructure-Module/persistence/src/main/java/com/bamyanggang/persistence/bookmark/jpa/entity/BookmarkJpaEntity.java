package com.bamyanggang.persistence.bookmark.jpa.entity;

import com.bamyanggang.domainmodule.domain.bookmark.enums.BookmarkStatus;
import com.bamyanggang.persistence.common.UUIDBinaryConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "bookmark")
public class BookmarkJpaEntity {

    @Id
    @Column(name = "bookmarkId", columnDefinition = "BINARY(16)")
    @Convert(converter = UUIDBinaryConverter.class)
    private UUID bookmarkId;

    @Column(name = "bookmark_status", columnDefinition = "VARCHAR(255)")
    @Enumerated(EnumType.STRING)
    private BookmarkStatus bookmarkStatus;

    @Column(name = "job_description_id", columnDefinition = "BINARY(16)")
    @Convert(converter = UUIDBinaryConverter.class)
    private UUID jobDescriptionId;

    @Column(name = "experience_id", columnDefinition = "BINARY(16)")
    @Convert(converter = UUIDBinaryConverter.class)
    private UUID experienceId;

    public BookmarkJpaEntity(UUID bookmarkId, BookmarkStatus bookmarkStatus, UUID jobDescriptionId, UUID experienceId) {
        this.bookmarkId = bookmarkId;
        this.bookmarkStatus = bookmarkStatus;
        this.jobDescriptionId = jobDescriptionId;
        this.experienceId = experienceId;
    }

}
