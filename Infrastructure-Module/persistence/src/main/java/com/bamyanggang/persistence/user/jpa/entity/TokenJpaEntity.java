package com.bamyanggang.persistence.user.jpa.entity;

import jakarta.persistence.Column;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Table(name = "token")
public class TokenJpaEntity {
    @Id
    @Column(name = "token_id", columnDefinition = "BINARY(16)")
    private UUID id;

    private UUID userId;

    @Column(columnDefinition = "VARCHAR(512)")
    private String value;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static TokenJpaEntity of(UUID id, UUID userId, String value, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new TokenJpaEntity(id, userId, value,createdAt, updatedAt);
    }
}
