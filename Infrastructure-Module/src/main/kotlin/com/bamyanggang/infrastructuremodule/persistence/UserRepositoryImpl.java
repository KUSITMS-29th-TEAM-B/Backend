package com.bamyanggang.infrastructuremodule.persistence;

import com.bamyanggang.domainmodule.domain.user.aggregate.User;
import com.bamyanggang.domainmodule.domain.user.repository.UserRepository;
import com.bamyanggang.infrastructuremodule.persistence.user.jpa.entity.UserJpaEntity;
import com.bamyanggang.infrastructuremodule.persistence.user.jpa.repository.UserJpaRepository;
import com.bamyanggang.infrastructuremodule.persistence.user.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    @Override
    public void save(@NotNull User user) {
        UserJpaEntity userJpaEntity = userMapper.toJpaEntity(user);
        userJpaRepository.save(userJpaEntity);
    }

}
