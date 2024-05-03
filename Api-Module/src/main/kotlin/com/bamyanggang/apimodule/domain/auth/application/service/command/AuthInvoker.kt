package com.bamyanggang.apimodule.domain.auth.application.service.command

import com.bamyanggang.apimodule.domain.auth.application.dto.SocialLogin
import com.bamyanggang.apimodule.domain.auth.application.exception.AuthException
import com.bamyanggang.apimodule.domain.auth.application.service.command.handler.AuthHandler
import com.bamyanggang.commonmodule.util.TransactionUtils
import com.bamyanggang.domainmodule.auth.enum.SocialLoginProvider
import com.bamyanggang.domainmodule.auth.jwt.Claims
import com.bamyanggang.domainmodule.auth.jwt.JwtProvider
import org.springframework.stereotype.Component

@Component
class AuthInvoker (
    private val authHandlerList: List<AuthHandler>,
    private val jwtProvider: JwtProvider,
    private val userReader: UserReader
    ) {

    fun invoke(request: SocialLogin.Request, provider: SocialLoginProvider): SocialLogin.Response {
        val response = attemptLogin(request, provider)
        return TransactionUtils.writable {
            return@writable userReader.findBySocialIdOrNull(response.socialId)?.let {
                val accessToken = jwtProvider.generateAccessToken(Claims.UserClaims(it.id))
                val refreshToken = jwtProvider.generateRefreshToken(Claims.UserClaims(it.id))
                SocialLogin.Response.Success(accessToken, refreshToken)
            } ?: run {
                val registrationToken = jwtProvider.generateRegistrationToken(Claims.RegistrationClaims(response.socialId, provider))
                SocialLogin.Response.UnRegistered(registrationToken, response.nickName)
            }
        }
    }

    private fun attemptLogin(request: SocialLogin.Request, provider: SocialLoginProvider): AuthHandler.Response {
        return authHandlerList.find { authHandler: AuthHandler -> authHandler.isAccessible(provider) }
            ?.handle(AuthHandler.Request(request.accessToken))
            ?: throw AuthException.OAuthFailed()
    }
}