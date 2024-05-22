package com.bamyanggang.persistence.user;

import com.bamyanggang.domainmodule.domain.user.aggregate.Token;
import com.bamyanggang.domainmodule.domain.user.repository.TokenRepository;
import com.bamyanggang.persistence.common.exception.PersistenceException;
import com.bamyanggang.persistence.user.jpa.entity.TokenJpaEntity;
import com.bamyanggang.persistence.user.jpa.repository.TokenJpaRepository;
import com.bamyanggang.persistence.user.mapper.UserMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TokenRepositoryImpl implements TokenRepository {
    private final TokenJpaRepository tokenJpaRepository;
    private final UserMapper userMapper;

    @Override
    public void save(Token token) {
        TokenJpaEntity tokenJpaEntity = userMapper.toJpaEntity(token);
        tokenJpaRepository.save(tokenJpaEntity);
    }

    @Override
    public Token findByValue(String value) {
        TokenJpaEntity tokenJpaEntity = tokenJpaRepository.findByValue(value)
                .orElseThrow(() -> new PersistenceException.NotFound());
        return userMapper.toDomainEntity(tokenJpaEntity);
    }

    @Override
    public void deleteByValue(@NotNull String value) {
        tokenJpaRepository.deleteByValue(value);
    }

    @Override
    public Token findByUserId(@NotNull UUID userId) {
        return tokenJpaRepository.findByUserId(userId)
                .map(userMapper::toDomainEntity)
                .orElse(null);
    }

}
