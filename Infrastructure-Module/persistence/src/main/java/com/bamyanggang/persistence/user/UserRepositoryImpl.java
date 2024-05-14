package com.bamyanggang.persistence.user;

import com.bamyanggang.domainmodule.domain.user.aggregate.User;
import com.bamyanggang.domainmodule.domain.user.repository.UserRepository;
import com.bamyanggang.persistence.common.exception.PersistenceException;
import com.bamyanggang.persistence.user.jpa.entity.UserJpaEntity;
import com.bamyanggang.persistence.user.jpa.repository.UserJpaRepository;
import com.bamyanggang.persistence.user.mapper.UserMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    @Override
    public void save(User user) {
        UserJpaEntity userJpaEntity = userMapper.toJpaEntity(user);
        userJpaRepository.save(userJpaEntity);
    }

    @Override
    public User findBySocialId(String socialId) {
        // 회원이 아닌 경우, null을 반환(registerToken 반환을 위해)
        return userJpaRepository.findBySocialId(socialId)
                .map(userMapper::toDomainEntity)
                .orElse(null);
    }

    @Override
    public User findById(@NotNull UUID userId) {
        return userJpaRepository.findByUserId(userId)
                .map(userMapper::toDomainEntity)
                .orElseThrow(() -> new PersistenceException.NotFound());
    }
}