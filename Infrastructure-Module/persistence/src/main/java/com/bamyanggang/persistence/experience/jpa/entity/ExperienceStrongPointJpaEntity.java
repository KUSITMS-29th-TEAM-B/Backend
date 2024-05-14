package com.bamyanggang.persistence.experience.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;

@Entity
@Getter
@Table(name = "experience_strong_point")
public class ExperienceStrongPointJpaEntity {
    @Id
    private UUID id;

    private UUID experienceId;

    private UUID strongPointId;
}
