package com.bamyanggang.persistence.user.jpa.entity;

import com.bamyanggang.domainmodule.domain.user.enums.SocialLoginProvider;
import jakarta.persistence.Column;
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
@AllArgsConstructor
@Getter
@Table(name = "user")
public class UserJpaEntity {
    @Id
    @Column(name = "user_id", columnDefinition = "BINARY(16)")
    private UUID id;

    private String socialId;

    private String profileImgUrl;

    @Enumerated(value = EnumType.STRING)
    private SocialLoginProvider provider;

    private String nickName;

    private String jobSearchStatus;

    private String desiredJob;

    @Column(columnDefinition = "VARCHAR(1200)")
    private String goal;

    @Column(columnDefinition = "VARCHAR(1200)")
    private String dream;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static UserJpaEntity of(UUID id, String socialId, String profileImgUrl, SocialLoginProvider provider, String nickName,
                                     String jobSearchStatus, String desiredJob, String goal, String dream
                                        , LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new UserJpaEntity(id, socialId, profileImgUrl, provider, nickName, jobSearchStatus,
                desiredJob, goal, dream, createdAt, updatedAt);
    }

}
