package com.bamyanggang.apimodule.domain.user.config

import com.bamyanggang.domainmodule.domain.user.repository.TokenRepository
import com.bamyanggang.domainmodule.domain.user.repository.UserRepository
import com.bamyanggang.domainmodule.domain.user.service.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UserServiceConfiguration(
    private val tokenRepository: TokenRepository,
    private val userRepository: UserRepository
) {

    @Bean
    fun tokenAppender(): TokenAppender {
        return TokenAppender(tokenRepository)
    }

    @Bean
    fun tokenReader(): TokenReader {
        return TokenReader(tokenRepository)
    }

    @Bean
    fun tokenRemover(): TokenRemover {
        return TokenRemover(tokenRepository)
    }

    @Bean
    fun userAppender(): UserAppender {
        return UserAppender(userRepository)
    }

    @Bean
    fun userModifier(): UserModifier {
        return UserModifier(userRepository)
    }

    @Bean
    fun userReader(): UserReader {
        return UserReader(userRepository)
    }


}
