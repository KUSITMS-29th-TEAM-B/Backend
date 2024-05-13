package com.bamyanggang.apimodule.domain.user.application.service.handler.impl

import com.bamyanggang.apimodule.domain.user.application.exception.AuthException
import com.bamyanggang.client.KakaoOAuthClient
import com.bamyanggang.apimodule.domain.user.application.service.handler.AuthHandler
import com.bamyanggang.domainmodule.domain.user.enums.SocialLoginProvider
import org.springframework.stereotype.Component

@Component
class KakaoOAuthHandler(
    private val kakaoOAuthClient: KakaoOAuthClient
) : AuthHandler {
    override val provider: SocialLoginProvider = SocialLoginProvider.KAKAO
    override fun handle(request: AuthHandler.Request): AuthHandler.Response {
        val kakaoUserInfo = kakaoOAuthClient.retrieveUserInfo(request.accessToken)?: throw AuthException.KakaoUserInfoRetrievalException()
        return AuthHandler.Response(kakaoUserInfo.id, kakaoUserInfo.kakaoAccount.nickname)
    }
}