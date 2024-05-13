package com.bamyanggang.apimodule

import com.bamyanggang.apimodule.domain.user.application.service.TokenService
import com.bamyanggang.jwt.JwtProvider
import com.bamyanggang.jwt.TokenExtractor
import org.mockito.Mockito.mock
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class FilterConfig {

    @Bean
    fun tokenService(): TokenService {
        return mock(TokenService::class.java)
    }

    @Bean
    fun tokenExtractor(): TokenExtractor {
        return mock(TokenExtractor::class.java)
    }
}