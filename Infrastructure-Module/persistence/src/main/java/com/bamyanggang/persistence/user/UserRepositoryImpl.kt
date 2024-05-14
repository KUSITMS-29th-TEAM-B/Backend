package com.bamyanggang.persistence.user

import com.bamyanggang.domainmodule.domain.user.aggregate.User
import com.bamyanggang.domainmodule.domain.user.repository.UserRepository
import com.bamyanggang.persistence.user.jpa.repository.UserJpaRepository
import com.bamyanggang.persistence.user.mapper.UserMapper
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(
    private val userJpaRepository: UserJpaRepository,
    private val userMapper: UserMapper
) : UserRepository {
    override fun save(user: User) {
        TODO("Not yet implemented")
    }

    override fun findBySocialId(socialId: String): User {
        TODO("Not yet implemented")
    }

}