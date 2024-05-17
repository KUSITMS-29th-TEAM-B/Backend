package com.bamyanggang.persistence.strongpoint.jpa.entity;

import com.bamyanggang.persistence.common.UUIDBinaryConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "strong_point")
public class StrongPointJpaEntity {
    @Id
    @Column(name = "strong_point_id")
    @Convert(converter = UUIDBinaryConverter.class)
    private UUID strongPointId;

    private String name;

    @Column(name = "user_id")
    @Convert(converter = UUIDBinaryConverter.class)
    private UUID userId;

    public static StrongPointJpaEntity of(UUID id, String name, UUID userId) {
        return new StrongPointJpaEntity(id, name, userId);
    }
}
