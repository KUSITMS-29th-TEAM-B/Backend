package com.bamyanggang.apimodule.domain.auth.application.service.command.handler.impl

import com.bamyanggang.apimodule.domain.auth.application.exception.AuthException
import com.bamyanggang.infrastructuremodule.client.KakaoOAuthClient
import com.bamyanggang.apimodule.domain.auth.application.service.command.handler.AuthHandler
import com.bamyanggang.domainmodule.domain.auth.enum.SocialLoginProvider
import org.springframework.stereotype.Component

@Component
class KakaoOAuthHandler(
    private val kakaoOAuthClient: KakaoOAuthClient
) : AuthHandler {
    override val provider: SocialLoginProvider = SocialLoginProvider.KAKAO

    override fun isAccessible(provider: SocialLoginProvider): Boolean {
        return this.provider == provider
    }

    override fun handle(request: AuthHandler.Request): AuthHandler.Response {
        val kakaoUserInfo = kakaoOAuthClient.retrieveUserInfo(request.accessToken)?: throw AuthException.KakaoUserInfoRetrievalException()
        return AuthHandler.Response(kakaoUserInfo.id, kakaoUserInfo.kakaoAccount.nickname)
    }
}