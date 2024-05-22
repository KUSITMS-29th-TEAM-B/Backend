package com.bamyanggang.persistence.keword.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "keyword")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class KeywordJpaEntity {
    @Id
    @Column(name = "keyword_id", columnDefinition = "BINARY(16)")
    private UUID keywordId;

    @Column(name = "user_id", columnDefinition = "BINARY(16)")
    private UUID userId;

    public KeywordJpaEntity(UUID keywordId, UUID userId) {
        this.keywordId = keywordId;
        this.userId = userId;
    }
}
