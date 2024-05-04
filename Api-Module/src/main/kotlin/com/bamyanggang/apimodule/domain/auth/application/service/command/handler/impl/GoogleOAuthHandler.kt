package com.bamyanggang.apimodule.domain.auth.application.service.command.handler.impl

import com.bamyanggang.apimodule.domain.auth.application.service.client.GoogleOAuthClient
import com.bamyanggang.apimodule.domain.auth.application.service.command.handler.AuthHandler
import com.bamyanggang.domainmodule.auth.enum.SocialLoginProvider
import org.springframework.stereotype.Component

@Component
class GoogleOAuthHandler(
    private val googleOAuthClient: GoogleOAuthClient
) : AuthHandler {
    override val provider: SocialLoginProvider = SocialLoginProvider.GOOGLE

    override fun isAccessible(provider: SocialLoginProvider): Boolean {
        return this.provider == provider
    }

    override fun handle(request: AuthHandler.Request): AuthHandler.Response {
        val googleUserInfo = googleOAuthClient.retrieveUserInfo(request.accessToken)?: throw IllegalAccessException("구글 사용자 정보를 가져오는데 실패했습니다.")
        return AuthHandler.Response(googleUserInfo.id, googleUserInfo.name)
    }
}