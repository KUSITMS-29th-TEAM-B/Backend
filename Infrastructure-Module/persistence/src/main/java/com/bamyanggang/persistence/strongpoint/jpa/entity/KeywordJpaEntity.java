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
    @Column(name = "keyword_id", columnDefinition = "BINARY(32)")
    private UUID keywordId;


    private String name;

    public KeywordJpaEntity(UUID keywordId, String name) {
        this.keywordId = keywordId;
        this.name = name;
    }
}
