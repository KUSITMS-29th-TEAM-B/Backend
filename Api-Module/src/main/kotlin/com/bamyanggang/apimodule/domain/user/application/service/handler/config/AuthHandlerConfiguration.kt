package com.bamyanggang.apimodule.domain.user.application.service.handler.config

import com.bamyanggang.apimodule.domain.user.application.service.handler.AuthHandler
import com.bamyanggang.apimodule.domain.user.application.service.handler.impl.GoogleOAuthHandler
import com.bamyanggang.apimodule.domain.user.application.service.handler.impl.KakaoOAuthHandler
import com.bamyanggang.domainmodule.domain.user.enums.SocialLoginProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AuthHandlerConfiguration(
    private val kakaoOAuthHandler: KakaoOAuthHandler,
    private val googleOAuthHandler: GoogleOAuthHandler
) {

    @Bean
    fun socialLoginHandlerMap(): Map<SocialLoginProvider, AuthHandler>{
        return mapOf(
            kakaoOAuthHandler.provider to kakaoOAuthHandler,
            googleOAuthHandler.provider to googleOAuthHandler
        )
    }
}