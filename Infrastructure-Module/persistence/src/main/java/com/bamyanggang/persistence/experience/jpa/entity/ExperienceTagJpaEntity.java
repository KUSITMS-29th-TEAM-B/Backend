package com.bamyanggang.persistence.experience.jpa.entity;

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
@Table(name = "experience_tag")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExperienceTagJpaEntity {
    @Id
    @Column(name = "experience_tag_id", columnDefinition = "BINARY(16)")
    @Convert(converter = UUIDBinaryConverter.class)
    private UUID experienceTagId;

    @Column(name = "experience_id", columnDefinition = "BINARY(16)")
    private UUID experienceId;

    @Column(name = "tag_id", columnDefinition = "BINARY(16)")
    private UUID tagId;
}
