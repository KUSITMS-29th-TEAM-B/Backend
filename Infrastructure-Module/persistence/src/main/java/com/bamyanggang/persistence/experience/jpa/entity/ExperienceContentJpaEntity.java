package com.bamyanggang.persistence.experience.jpa.entity;

import com.bamyanggang.persistence.common.UUIDBinaryConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "experience_content")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ExperienceContentJpaEntity {
    @Id
    @Column(name = "experience_content_id", columnDefinition = "BINARY(16)")
    @Convert(converter = UUIDBinaryConverter.class)
    private UUID experienceContentId;

    @Column(columnDefinition = "VARCHAR(255)")
    private String question;

    @Column(columnDefinition = "VARCHAR(6000)")
    private String answer;

    @Column(name = "experience_id", columnDefinition = "BINARY(16)")
    @Convert(converter = UUIDBinaryConverter.class)
    private UUID experienceId;

    public ExperienceContentJpaEntity(UUID id, String question, String answer, UUID experienceId) {
        this.experienceContentId = id;
        this.question = question;
        this.answer = answer;
        this.experienceId = experienceId;
    }
}
