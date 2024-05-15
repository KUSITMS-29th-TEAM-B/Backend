package com.bamyanggang.persistence.strongpoint.jpa.entity;

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
    private UUID id;

    private String name;

    private UUID userId;

    public static StrongPointJpaEntity of(UUID id, String name, UUID userId) {
        return new StrongPointJpaEntity(id, name, userId);
    }
}
