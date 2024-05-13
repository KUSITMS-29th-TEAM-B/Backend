package com.bamyanggang.apimodule.domain.user.application.service

import com.bamyanggang.apimodule.domain.user.application.dto.SocialLogin
import com.bamyanggang.apimodule.domain.user.application.exception.AuthException
import com.bamyanggang.apimodule.domain.user.application.service.handler.AuthHandler
import com.bamyanggang.commonmodule.util.TransactionUtils
import com.bamyanggang.domainmodule.domain.user.enums.SocialLoginProvider
import com.bamyanggang.domainmodule.domain.user.service.TokenAppender
import com.bamyanggang.domainmodule.domain.user.service.UserReader
import com.bamyanggang.jwt.Claims
import com.bamyanggang.jwt.JwtProvider
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val authHandlerMap: Map<SocialLoginProvider, AuthHandler>,
    private val jwtProvider: JwtProvider,
    private val userReader: UserReader,
    private val tokenAppender: TokenAppender
){
    fun executeSocialLogin(provider: SocialLoginProvider, request: SocialLogin.Request): SocialLogin.Response {
        val socialLoginHandler = authHandlerMap[provider] ?: throw AuthException.OAuthFailed()
        val response = socialLoginHandler.handle(AuthHandler.Request(request.accessToken))
        return TransactionUtils.writable {
            return@writable userReader.readUserBySocialId(response.socialId)?.let {
                val accessToken = jwtProvider.generateAccessToken(Claims.UserClaims(it.id))
                val refreshToken = jwtProvider.generateRefreshToken(Claims.UserClaims(it.id))
                tokenAppender.appendToken(it, refreshToken)
                SocialLogin.Response.Success(accessToken, refreshToken)
            } ?: run {
                val registrationToken = jwtProvider.generateRegistrationToken(Claims.RegistrationClaims(response.socialId))
                SocialLogin.Response.UnRegistered(registrationToken, response.nickName)
            }
        }
    }
}