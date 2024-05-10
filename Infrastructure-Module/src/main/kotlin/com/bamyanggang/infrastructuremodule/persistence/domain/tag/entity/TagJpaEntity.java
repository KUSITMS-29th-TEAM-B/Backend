package com.bamyanggang.infrastructuremodule.persistence.domain.tag.entity;

import static jakarta.persistence.FetchType.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tag")
public class TagJpaEntity {
    @Id
    @Column(name = "tag_id")
    private UUID id;

    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_tag_id")
    private TagJpaEntity parentTag;

    @Builder
    public TagJpaEntity(UUID id, String name, TagJpaEntity parentTag) {
        this.id = id;
        this.name = name;
        this.parentTag = parentTag;
    }
}
