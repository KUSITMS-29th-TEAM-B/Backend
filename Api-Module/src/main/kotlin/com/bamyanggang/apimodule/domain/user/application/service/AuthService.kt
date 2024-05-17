package com.bamyanggang.apimodule.domain.user.application.service

import com.bamyanggang.apimodule.domain.user.application.dto.Logout
import com.bamyanggang.apimodule.domain.user.application.dto.Reissue
import com.bamyanggang.apimodule.domain.user.application.dto.SocialLogin
import com.bamyanggang.apimodule.domain.user.application.service.handler.AuthHandler
import com.bamyanggang.apimodule.domain.user.application.service.handler.AuthHandlerManager
import com.bamyanggang.cache.CacheManager
import com.bamyanggang.commonmodule.util.TransactionUtils
import com.bamyanggang.domainmodule.domain.user.enums.SocialLoginProvider
import com.bamyanggang.domainmodule.domain.user.service.TokenAppender
import com.bamyanggang.domainmodule.domain.user.service.TokenReader
import com.bamyanggang.domainmodule.domain.user.service.TokenRemover
import com.bamyanggang.domainmodule.domain.user.service.UserReader
import com.bamyanggang.jwt.*
import com.bamyanggang.lock.LockManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val authHandlerManager: AuthHandlerManager,
    private val jwtProvider: JwtProvider,
    private val jwtValidator: JwtValidator,
    private val userReader: UserReader,
    private val tokenAppender: TokenAppender,
    private val claimsExtractor: ClaimsExtractor,
    private val tokenRemover: TokenRemover,
    private val tokenReader : TokenReader
){
    fun executeSocialLogin(provider: SocialLoginProvider, request: SocialLogin.Request): SocialLogin.Response {
        val socialLoginHandler = authHandlerManager.getHandler(provider)
        val response = socialLoginHandler.handle(AuthHandler.Request(request.accessToken))
        return TransactionUtils.writable {
            return@writable userReader.readUserBySocialId(response.socialId)?.let {
                val accessToken = jwtProvider.generateAccessToken(Claims.UserClaims(it.id))
                val refreshToken = jwtProvider.generateRefreshToken(Claims.UserClaims(it.id))
                tokenAppender.appendToken(it.id, refreshToken)
                SocialLogin.Response.Success(accessToken, refreshToken)
            } ?: run {
                val registrationToken = jwtProvider.generateRegistrationToken(Claims.RegistrationClaims(response.socialId))
                SocialLogin.Response.UnRegistered(registrationToken, response.nickName)
            }
        }
    }

    @Transactional
    fun reissueToken(reissueRequest: Reissue.Request): Reissue.Response = LockManager.lockByKey(reissueRequest.refreshToken) {
        CacheManager.cacheByKey(reissueRequest.refreshToken) {
            jwtValidator.validateToken(reissueRequest.refreshToken, TokenType.REFRESH_TOKEN)
            val userClaims: Claims.UserClaims = claimsExtractor.extractClaimsFromToken(
                reissueRequest.refreshToken, TokenType.REFRESH_TOKEN,
                JwtConst.USER_CLAIMS, Claims.UserClaims::class.java
            )
            val accessToken = jwtProvider.generateAccessToken(userClaims)
            val newRefreshToken = jwtProvider.generateRefreshToken(userClaims)

            tokenAppender.appendToken(userClaims.userId, newRefreshToken)
            tokenReader.readToken(reissueRequest.refreshToken).also { tokenRemover.removeToken(it) }

            return@cacheByKey Reissue.Response(accessToken, newRefreshToken)
        }
    }

    @Transactional
    fun logout(logoutRequest: Logout.Request) {
        tokenReader.readToken(logoutRequest.refreshToken).also { tokenRemover.removeToken(it) }
    }
}
