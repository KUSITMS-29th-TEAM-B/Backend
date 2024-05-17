package com.bamyanggang.persistence.user.jpa.entity;

import com.bamyanggang.domainmodule.domain.user.enums.SocialLoginProvider;
import com.bamyanggang.persistence.common.UUIDBinaryConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "user")
public class UserJpaEntity {
    @Id
    @Column(name = "user_id", columnDefinition = "BINARY(16)")
    @Convert(converter = UUIDBinaryConverter.class)
    private UUID userId;

    private String socialId;

    private String profileImgUrl;

    @Enumerated(value = EnumType.STRING)
    private SocialLoginProvider provider;

    private String email;

    private String nickName;

    private String jobSearchStatus;

    private String desiredJob;

    @Column(columnDefinition = "VARCHAR(1200)")
    private String goal;

    @Column(columnDefinition = "VARCHAR(1200)")
    private String dream;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public UserJpaEntity(UUID userId, String socialId, String profileImgUrl, SocialLoginProvider provider, String email,
                         String nickName, String jobSearchStatus, String desiredJob, String goal, String dream, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.socialId = socialId;
        this.profileImgUrl = profileImgUrl;
        this.provider = provider;
        this.email = email;
        this.nickName = nickName;
        this.jobSearchStatus = jobSearchStatus;
        this.desiredJob = desiredJob;
        this.goal = goal;
        this.dream = dream;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
