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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "experience_content")
public class ExperienceContentJpaEntity {
    @Id
    @Column(name = "experience_content_id")
    @Convert(converter = UUIDBinaryConverter.class)
    private UUID experienceContentId;

    @Column(columnDefinition = "TEXT")
    private String question;

    @Column(columnDefinition = "TEXT")
    private String answer;

    public static ExperienceContentJpaEntity of(UUID id, String question, String answer) {
        return new ExperienceContentJpaEntity(id, question, answer);
    }
}
