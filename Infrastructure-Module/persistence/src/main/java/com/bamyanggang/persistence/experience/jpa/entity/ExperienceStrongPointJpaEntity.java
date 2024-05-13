package com.bamyanggang.persistence.experience.jpa.entity;

import static jakarta.persistence.FetchType.*;

import com.bamyanggang.persistence.strongpoint.jpa.entity.StrongPointJpaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;

@Entity
@Getter
@Table(name = "experience_strong_point")
public class ExperienceStrongPointJpaEntity {
    @Id
    private UUID id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "experience_id")
    private ExperienceJpaEntity experience;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "strong_point_id")
    private StrongPointJpaEntity strongPoint;
}
