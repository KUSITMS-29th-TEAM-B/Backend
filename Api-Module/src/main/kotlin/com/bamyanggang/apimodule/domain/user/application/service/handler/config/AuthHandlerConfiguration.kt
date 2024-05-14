package com.bamyanggang.apimodule.domain.user.application.service.handler.config

import com.bamyanggang.apimodule.domain.user.application.service.handler.AuthHandlerManager
import com.bamyanggang.apimodule.domain.user.application.service.handler.impl.GoogleOAuthHandler
import com.bamyanggang.apimodule.domain.user.application.service.handler.impl.KakaoOAuthHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AuthHandlerConfiguration(
    private val kakaoOAuthHandler: KakaoOAuthHandler,
    private val googleOAuthHandler: GoogleOAuthHandler
) {

    @Bean
    fun authHandlerManager(): AuthHandlerManager {
        return AuthHandlerManager(kakaoOAuthHandler, googleOAuthHandler)
    }
}