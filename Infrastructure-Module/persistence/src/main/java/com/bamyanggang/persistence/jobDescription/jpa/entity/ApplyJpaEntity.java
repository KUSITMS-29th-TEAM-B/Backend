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
@Table(name = "apply")
public class ApplyJpaEntity {

    @Id
    @Column(name = "apply_id", columnDefinition = "BINARY(16)")
    @Convert(converter = UUIDBinaryConverter.class)
    private UUID applyId;

    private String title;

    @Enumerated(EnumType.STRING)
    private WriteStatus writeStatus;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Column(name = "job_description_id", columnDefinition = "BINARY(16)")
    @Convert(converter = UUIDBinaryConverter.class)
    private UUID jobDescriptionId;

    public ApplyJpaEntity(UUID applyId, String title, WriteStatus writeStatus, LocalDateTime createdAt, LocalDateTime updatedAt, UUID jobDescriptionId) {
        this.applyId = applyId;
        this.title = title;
        this.writeStatus = writeStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.jobDescriptionId = jobDescriptionId;
    }

}
