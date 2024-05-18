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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "experience")
public class ExperienceJpaEntity{
    @Id
    @Column(name = "experience_id", columnDefinition = "BINARY(16)")
    @Convert(converter = UUIDBinaryConverter.class)
    private UUID experienceId;

    private String title;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    private UUID userId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static ExperienceJpaEntity of(
            UUID id,
            String title,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            UUID userId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        return new ExperienceJpaEntity(id, title, startedAt, endedAt, userId, createdAt, updatedAt);
    }
}


