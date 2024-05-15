package com.bamyanggang.apimodule.domain.user.application.service

import com.bamyanggang.apimodule.domain.user.application.dto.Logout
import com.bamyanggang.apimodule.domain.user.application.dto.Reissue
import com.bamyanggang.apimodule.domain.user.application.dto.SocialLogin
import com.bamyanggang.apimodule.domain.user.application.service.handler.AuthHandler
import com.bamyanggang.apimodule.domain.user.application.service.handler.AuthHandlerManager
import com.bamyanggang.commonmodule.util.TransactionUtils
import com.bamyanggang.domainmodule.domain.user.enums.SocialLoginProvider
import com.bamyanggang.domainmodule.domain.user.service.UserReader
import com.bamyanggang.jwt.Claims
import com.bamyanggang.jwt.JwtProvider
import com.bamyanggang.jwt.JwtRegistry
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val authHandlerManager: AuthHandlerManager,
    private val jwtProvider: JwtProvider,
    private val userReader: UserReader,
    private val jwtRegistry: JwtRegistry
){
    fun executeSocialLogin(provider: SocialLoginProvider, request: SocialLogin.Request): SocialLogin.Response {
        val socialLoginHandler = authHandlerManager.getHandler(provider)
        val response = socialLoginHandler.handle(AuthHandler.Request(request.accessToken))
        return TransactionUtils.writable {
            return@writable userReader.readUserBySocialId(response.socialId)?.let {
                val accessToken = jwtProvider.generateAccessToken(Claims.UserClaims(it.id))
                val refreshToken = jwtProvider.generateRefreshToken(Claims.UserClaims(it.id))
                SocialLogin.Response.Success(accessToken, refreshToken)
            } ?: run {
                val registrationToken = jwtProvider.generateRegistrationToken(Claims.RegistrationClaims(response.socialId))
                SocialLogin.Response.UnRegistered(registrationToken, response.nickName)
            }
        }
    }

    @Transactional
    fun reissueToken(reissueRequest: Reissue.Request): Reissue.Response {
        val (accessToken, refreshToken) = jwtProvider.reissueToken(reissueRequest.refreshToken)
        return Reissue.Response(accessToken, refreshToken)
    }

    @Transactional
    fun logout(logoutRequest: Logout.Request) {
        jwtRegistry.delete(logoutRequest.refreshToken)
    }
}