package com.bamyanggang.infrastructuremodule.persistence.experience.jpa.entity;

import static jakarta.persistence.FetchType.*;

import com.bamyanggang.infrastructuremodule.persistence.tag.jpa.entity.TagJpaEntity;
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

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "experience_id")
    private ExperienceJpaEntity experience;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "tag_id")
    private TagJpaEntity tag;
}
