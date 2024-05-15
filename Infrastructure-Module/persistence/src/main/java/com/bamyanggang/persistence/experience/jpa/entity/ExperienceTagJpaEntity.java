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
@Table(name = "experience_tag")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExperienceTagJpaEntity {
    @Id
    @Column(name = "experience_tag_id")
    private UUID experienceTagId;

    private UUID experienceId;

    private UUID tagId;
}
