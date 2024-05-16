package com.bamyanggang.persistence.jobDescription.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "job_description")
public class JobDescriptionJpaEntity {

    @Id
    @Column(name = "job_description_id", columnDefinition = "BINARY(16)")
    private UUID jobDescriptionId;

    private String enterpriseName;

    private String title;

    @Column(columnDefinition = "VARCHAR(6000)")
    private String content;

    private String link;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    @Column(name = "user_id", columnDefinition = "BINARY(16)")
    private UUID userId;


    public JobDescriptionJpaEntity(UUID jobDescriptionId, String enterpriseName, String title, String content,
                                   String link, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime startedAt,
                                   LocalDateTime endedAt, UUID userId) {
        this.jobDescriptionId = jobDescriptionId;
        this.enterpriseName = enterpriseName;
        this.title = title;
        this.content = content;
        this.link = link;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.userId = userId;
    }


}
