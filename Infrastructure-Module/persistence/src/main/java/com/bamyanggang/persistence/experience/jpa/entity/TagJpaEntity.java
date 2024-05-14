package com.bamyanggang.persistence.experience.jpa.entity;

import static jakarta.persistence.FetchType.LAZY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @Column(name = "tag_id")
    private UUID id;

    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_tag_id")
    private TagJpaEntity parentTag;

    private UUID userId;

    public TagJpaEntity(UUID id, String name, TagJpaEntity parentTag, UUID userId) {
        this.id = id;
        this.name = name;
        this.parentTag = parentTag;
        this.userId = userId;
    }
}
