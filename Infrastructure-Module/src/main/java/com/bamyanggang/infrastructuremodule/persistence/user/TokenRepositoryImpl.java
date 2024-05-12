package com.bamyanggang.infrastructuremodule.persistence.user;

import com.bamyanggang.domainmodule.domain.user.aggregate.Token;
import com.bamyanggang.domainmodule.domain.user.repository.TokenRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

@Repository
public class TokenRepositoryImpl implements TokenRepository {
    @Override
    public void save(@NotNull Token token) {

    }
}
