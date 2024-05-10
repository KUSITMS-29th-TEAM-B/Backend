package com.bamyanggang.infrastructuremodule.persistence.domain.experience.entity;

import com.bamyanggang.infrastructuremodule.persistence.global.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "experience")
public class ExperienceJpaEntity extends BaseTimeEntity{
    @Id
    @Column(name = "experience_id")
    private UUID id;

    private String title;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    /*
     * Todo: User 연관관계 구현 필요
     */

    @Builder
    public ExperienceJpaEntity(UUID id, String title, LocalDateTime startedAt, LocalDateTime endedAt) {
        this.id = id;
        this.title = title;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }
}


