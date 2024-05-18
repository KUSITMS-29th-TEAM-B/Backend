package com.bamyanggang.persistence.experience.jpa.entity;

import com.bamyanggang.persistence.common.UUIDBinaryConverter;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "experience")
public class ExperienceJpaEntity{
    @Id
    @Column(name = "experience_id", columnDefinition = "BINARY(16)")
    @Convert(converter = UUIDBinaryConverter.class)
    private UUID experienceId;

    private String title;

    @Column(name = "parent_tag_id", columnDefinition = "BINARY(16)")
    @Convert(converter = UUIDBinaryConverter.class)
    private UUID parentTagId;

    @Column(name = "child_tag_tid", columnDefinition = "BINARY(16)")
    @Convert(converter = UUIDBinaryConverter.class)
    private UUID childTagId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "experience_content",
            joinColumns = {@JoinColumn(name = "experience_id")}
    )
    private List<ExperienceContentJpaEntity> contents;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "strong_point_info",
            joinColumns = {@JoinColumn(name = "experience_id")}
    )
    private List<StrongPointInfoJpaEntity> strongPointInfos;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Column(name = "user_id", columnDefinition = "BINARY(16)")
    @Convert(converter = UUIDBinaryConverter.class)
    private UUID userId;

    public ExperienceJpaEntity(
            UUID id,
            String title,
            UUID parentTagId,
            UUID childTagId,
            List<ExperienceContentJpaEntity> contents,
            List<StrongPointInfoJpaEntity> strongPointInfoJpaEntities,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            UUID userId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.experienceId = id;
        this.title = title;
        this.parentTagId = parentTagId;
        this.childTagId = childTagId;
        this.contents = contents;
        this.strongPointInfos = strongPointInfoJpaEntities;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}


