package com.bamyanggang.persistence.strongpoint.jpa.entity;

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

    @Column(name = "default_keyword_id", columnDefinition = "BINARY(16)")
    private UUID defaultKeywordId;

    public KeywordJpaEntity(UUID keywordId, UUID defaultKeywordId) {
        this.keywordId = keywordId;
        this.defaultKeywordId = defaultKeywordId;
    }
}
