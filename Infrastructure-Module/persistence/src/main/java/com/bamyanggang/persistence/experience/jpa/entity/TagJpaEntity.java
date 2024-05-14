package com.bamyanggang.persistence.experience.jpa.entity;

import jakarta.persistence.Column;
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
@Table(name = "tag")
public class TagJpaEntity {
    @Id
    @Column(name = "tag_id")
    private UUID id;

    private String name;

    private UUID parentTagId;

    private UUID userId;

    public static TagJpaEntity of(UUID id, String name, UUID parentTagId, UUID userId) {
        return new TagJpaEntity(id, name, parentTagId, userId);
    }
}
