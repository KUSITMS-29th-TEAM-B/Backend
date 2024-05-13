package com.bamyanggang.persistence.user;

import com.bamyanggang.domainmodule.domain.user.aggregate.Token;
import com.bamyanggang.domainmodule.domain.user.repository.TokenRepository;
import com.bamyanggang.persistence.user.jpa.entity.TokenJpaEntity;
import com.bamyanggang.persistence.user.jpa.repository.TokenJpaRepository;
import com.bamyanggang.persistence.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
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
}
