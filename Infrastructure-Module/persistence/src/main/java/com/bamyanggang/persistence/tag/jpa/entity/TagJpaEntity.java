package com.bamyanggang.persistence.tag.jpa.entity;

import jakarta.persistence.Column;
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
@Table(name = "tag")
public class TagJpaEntity {
    @Id
    @Column(name = "tag_id", columnDefinition = "BINARY(16)")
    private UUID tagId;

    private String name;

    @Column(name = "parent_tag_id", columnDefinition = "BINARY(16)")
    private UUID parentTagId;

    @Column(name = "user_id", columnDefinition = "BINARY(16)")
    private UUID userId;

    public TagJpaEntity (UUID id, String name, UUID parentTagId, UUID userId) {
        this.tagId = id;
        this.name = name;
        this.parentTagId = parentTagId;
        this.userId = userId;
    }
}
