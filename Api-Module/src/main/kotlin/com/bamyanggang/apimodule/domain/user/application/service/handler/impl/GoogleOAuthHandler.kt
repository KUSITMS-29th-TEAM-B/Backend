package com.bamyanggang.apimodule.domain.user.application.service.handler.impl

import com.bamyanggang.apimodule.domain.user.application.exception.AuthException
import com.bamyanggang.apimodule.domain.user.application.service.handler.AuthHandler
import com.bamyanggang.client.GoogleOAuthClient
import com.bamyanggang.domainmodule.domain.user.enums.SocialLoginProvider
import org.springframework.stereotype.Component

@Component
class GoogleOAuthHandler(
    private val googleOAuthClient: GoogleOAuthClient
) : AuthHandler {
    override val provider: SocialLoginProvider = SocialLoginProvider.GOOGLE
    override fun handle(request: AuthHandler.Request): AuthHandler.Response {
        val googleUserInfo = googleOAuthClient.retrieveUserInfo(request.accessToken)?: throw AuthException.GoogleUserInfoRetrievalException()
        return AuthHandler.Response(googleUserInfo.id, googleUserInfo.name, "email")
    }
}
