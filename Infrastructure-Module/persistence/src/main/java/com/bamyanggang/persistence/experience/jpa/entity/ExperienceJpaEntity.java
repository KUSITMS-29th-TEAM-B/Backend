package com.bamyanggang.persistence.experience.jpa.entity;

import com.bamyanggang.persistence.common.UUIDBinaryConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
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
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            UUID userId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.experienceId = id;
        this.title = title;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}


