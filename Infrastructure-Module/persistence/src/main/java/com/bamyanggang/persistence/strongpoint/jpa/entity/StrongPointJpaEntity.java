package com.bamyanggang.persistence.strongpoint.jpa.entity;

import com.bamyanggang.persistence.common.UUIDBinaryConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "strong_point")
public class StrongPointJpaEntity {
    @Id
    @Column(name = "strong_point_id", columnDefinition = "BINARY(16)")
    @Convert(converter = UUIDBinaryConverter.class)
    private UUID strongPointId;

    private String name;

    @Column(name = "user_id", columnDefinition = "BINARY(16)")
    @Convert(converter = UUIDBinaryConverter.class)
    private UUID userId;

    public StrongPointJpaEntity (UUID id, String name, UUID userId) {
        this.strongPointId = id;
        this.name = name;
        this.userId = userId;
    }
}
