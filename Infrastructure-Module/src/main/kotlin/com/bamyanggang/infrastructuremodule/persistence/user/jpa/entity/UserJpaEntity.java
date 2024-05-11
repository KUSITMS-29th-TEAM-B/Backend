package com.bamyanggang.infrastructuremodule.persistence.user.jpa.entity;

import com.bamyanggang.domainmodule.domain.user.enums.DesiredJob;
import com.bamyanggang.domainmodule.domain.user.enums.JobSearchStatus;
import com.bamyanggang.domainmodule.domain.user.enums.SocialLoginProvider;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserJpaEntity {
    @Id
    @Column(name = "user_id", columnDefinition = "BINARY(16)")
    private UUID id;

    private String socialId;

    private String profileImgUrl;

    @Enumerated(value = EnumType.STRING)
    private SocialLoginProvider provider;

    private String nickName;

    @Enumerated(value = EnumType.STRING)
    private JobSearchStatus jobSearchStatus;

    @Enumerated(value = EnumType.STRING)
    private DesiredJob desiredJob;

    private String goal;

    private String dream;

    public static UserJpaEntity from(UUID id, String socialId, String profileImgUrl, SocialLoginProvider provider, String nickName,
                                     JobSearchStatus jobSearchStatus, DesiredJob desiredJob, String goal, String dream) {
        return new UserJpaEntity(id, socialId, profileImgUrl, provider, nickName, jobSearchStatus, desiredJob, goal, dream);
    }

}