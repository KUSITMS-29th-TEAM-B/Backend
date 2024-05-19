package com.bamyanggang.persistence.experience.jpa.entity;

import com.bamyanggang.persistence.common.UUIDBinaryConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Table(name = "strong_point_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ExperienceStrongPointJpaEntity {
    @Column(name = "experience_strong_point_id", columnDefinition = "BINARY(16)")
    @Convert(converter = UUIDBinaryConverter.class)
    private UUID experienceStrongPointId;

    @Column(name = "strong_point_id", columnDefinition = "BINARY(16)")
    @Convert(converter = UUIDBinaryConverter.class)
    private UUID strongPointId;

    public ExperienceStrongPointJpaEntity(UUID strongPointInfoId, UUID strongPointId) {
        this.experienceStrongPointId = strongPointInfoId;
        this.strongPointId = strongPointId;
    }
}
