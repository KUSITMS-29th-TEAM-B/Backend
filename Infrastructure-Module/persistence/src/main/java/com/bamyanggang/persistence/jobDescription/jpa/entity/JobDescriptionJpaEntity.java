package com.bamyanggang.persistence.jobDescription.jpa.entity;

import com.bamyanggang.domainmodule.domain.jobDescription.enums.WriteStatus;
import com.bamyanggang.persistence.common.UUIDBinaryConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @Convert(converter = UUIDBinaryConverter.class)
    private UUID jobDescriptionId;

    private String enterpriseName;

    private String title;

    @Column(name = "write_status", columnDefinition = "VARCHAR(255)")
    @Enumerated(EnumType.STRING)
    private WriteStatus writeStatus;

    @Column(columnDefinition = "VARCHAR(6000)")
    private String content;

    private String link;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    @Column(name = "user_id", columnDefinition = "BINARY(16)")
    @Convert(converter = UUIDBinaryConverter.class)
    private UUID userId;


    public JobDescriptionJpaEntity(UUID jobDescriptionId, String enterpriseName, String title, WriteStatus writeStatus,
                                   String content, String link, LocalDateTime createdAt, LocalDateTime updatedAt,
                                   LocalDateTime startedAt, LocalDateTime endedAt, UUID userId) {
        this.jobDescriptionId = jobDescriptionId;
        this.enterpriseName = enterpriseName;
        this.title = title;
        this.writeStatus = writeStatus;
        this.content = content;
        this.link = link;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.userId = userId;
    }


}
