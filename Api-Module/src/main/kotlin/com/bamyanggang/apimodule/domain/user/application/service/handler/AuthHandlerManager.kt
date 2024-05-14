package com.bamyanggang.apimodule.domain.user.application.service.handler

import com.bamyanggang.apimodule.domain.user.application.exception.AuthException
import com.bamyanggang.domainmodule.domain.user.enums.SocialLoginProvider

class AuthHandlerManager(
    private val kakaoOAuthHandler: AuthHandler,
    private val googleOAuthHandler: AuthHandler
) {
    fun getHandler(provider: SocialLoginProvider): AuthHandler {
        return when (provider) {
            SocialLoginProvider.KAKAO -> kakaoOAuthHandler
            SocialLoginProvider.GOOGLE -> googleOAuthHandler
            else -> throw AuthException.OAuthFailed()
        }
    }
}