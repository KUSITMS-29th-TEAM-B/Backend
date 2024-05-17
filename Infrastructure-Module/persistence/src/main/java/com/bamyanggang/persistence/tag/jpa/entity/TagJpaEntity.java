package com.bamyanggang.persistence.tag.jpa.entity;

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
@Table(name = "tag")
public class TagJpaEntity {
    @Id
    @Column(name = "tag_id", columnDefinition = "BINARY(16)")
    @Convert(converter = UUIDBinaryConverter.class)
    private UUID tagId;

    private String name;

    private UUID parentTagId;

    private UUID userId;

    public static TagJpaEntity of(UUID id, String name, UUID parentTagId, UUID userId) {
        return new TagJpaEntity(id, name, parentTagId, userId);
    }
}
