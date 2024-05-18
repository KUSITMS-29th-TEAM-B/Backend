package com.bamyanggang.persistence.user.mapper;

import com.bamyanggang.domainmodule.domain.user.aggregate.Token;
import com.bamyanggang.domainmodule.domain.user.aggregate.User;
import com.bamyanggang.persistence.user.jpa.entity.TokenJpaEntity;
import com.bamyanggang.persistence.user.jpa.entity.UserJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserJpaEntity toJpaEntity(User user) {
        return new UserJpaEntity(
                user.getId(),
                user.getSocialId(),
                user.getProfileImgUrl(),
                user.getProvider(),
                user.getEmail(),
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
        return User.Companion.toDomain(userJpaEntity.getUserId(),
                userJpaEntity.getSocialId(),
                userJpaEntity.getProfileImgUrl(),
                userJpaEntity.getProvider(),
                userJpaEntity.getEmail(),
                userJpaEntity.getNickName(),
                userJpaEntity.getJobSearchStatus(),
                userJpaEntity.getDesiredJob(),
                userJpaEntity.getGoal(),
                userJpaEntity.getDream(),
                userJpaEntity.getCreatedAt(),
                userJpaEntity.getUpdatedAt()
        );
    }

    public TokenJpaEntity toJpaEntity(Token token) {
        return TokenJpaEntity.of(
                token.getId(),
                token.getUserId(),
                token.getValue(),
                token.getCreatedAt(),
                token.getUpdatedAt()
        );
    }

    public Token toDomainEntity(TokenJpaEntity tokenJpaEntity) {
        return Token.Companion.toDomain(tokenJpaEntity.getId(),
                tokenJpaEntity.getUserId(),
                tokenJpaEntity.getValue(),
                tokenJpaEntity.getCreatedAt(),
                tokenJpaEntity.getUpdatedAt()
        );
    }

}
