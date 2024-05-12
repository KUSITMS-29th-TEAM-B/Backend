package com.bamyanggang.infrastructuremodule.persistence.user.mapper;

import com.bamyanggang.domainmodule.domain.user.aggregate.User;
import com.bamyanggang.infrastructuremodule.persistence.user.jpa.entity.UserJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserJpaEntity toJpaEntity(User user) {
        return UserJpaEntity.from(
                user.getId(),
                user.getSocialId(),
                user.getProfileImgUrl(),
                user.getProvider(),
                user.getNickName(),
                user.getJobSearchStatus(),
                user.getDesiredJob(),
                user.getGoal(),
                user.getDream(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public User toDomainEntity(UserJpaEntity userJpaEntity) {
        return User.Companion.toDomain(userJpaEntity.getId(),
                userJpaEntity.getSocialId(),
                userJpaEntity.getProfileImgUrl(),
                userJpaEntity.getProvider(),
                userJpaEntity.getNickName(),
                userJpaEntity.getJobSearchStatus(),
                userJpaEntity.getDesiredJob(),
                userJpaEntity.getGoal(),
                userJpaEntity.getDream(),
                userJpaEntity.getCreatedAt(),
                userJpaEntity.getUpdatedAt()
        );
    }
}
