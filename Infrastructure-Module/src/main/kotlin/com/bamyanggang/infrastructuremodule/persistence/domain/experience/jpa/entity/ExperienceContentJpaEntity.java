package com.bamyanggang.infrastructuremodule.persistence.domain.experience.jpa.entity;

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
@Table(name = "experience_content")
public class ExperienceContentJpaEntity {
    @Id
    @Column(name = "experience_content_id")
    private UUID id;

    @Column(columnDefinition = "TEXT")
    private String question;

    @Column(columnDefinition = "TEXT")
    private String answer;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "experience_id")
    private ExperienceJpaEntity experience;

    @Builder
    public ExperienceContentJpaEntity(UUID id, String question, String answer, ExperienceJpaEntity experience) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.experience = experience;
    }
}
