package com.bamyanggang.persistence.experience.jpa.entity;

import static jakarta.persistence.FetchType.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;

@Entity
@Getter
@Table(name = "experience_tag")
public class ExperienceTagJpaEntity {
    @Id
    private UUID id;

    private UUID experienceId;

    private UUID tagId;
}
