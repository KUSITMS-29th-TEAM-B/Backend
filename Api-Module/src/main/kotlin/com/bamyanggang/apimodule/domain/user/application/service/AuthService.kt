package com.bamyanggang.apimodule.domain.user.application.service

import com.bamyanggang.apimodule.domain.user.application.dto.Reissue
import com.bamyanggang.apimodule.domain.user.application.dto.SocialLogin
import com.bamyanggang.apimodule.domain.user.application.service.handler.AuthHandler
import com.bamyanggang.apimodule.domain.user.application.service.handler.AuthHandlerManager
import com.bamyanggang.cache.CacheManager
import com.bamyanggang.commonmodule.util.TransactionUtils
import com.bamyanggang.domainmodule.domain.user.enums.SocialLoginProvider
import com.bamyanggang.domainmodule.domain.user.service.*
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
    private val tokenExtractor: TokenExtractor,
    private val tokenModifier: TokenModifier
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
                val registrationToken = jwtProvider.generateRegistrationToken(Claims.RegistrationClaims(response.socialId, response.email))
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

            tokenModifier.modifyToken(userClaims.userId, reissueRequest.refreshToken, newRefreshToken)

            return@cacheByKey Reissue.Response(accessToken, newRefreshToken)
        }
    }

    @Transactional
    fun logout(refreshToken: String) {
        val refreshToken = tokenExtractor.extractValue(refreshToken)
        tokenRemover.removeToken(refreshToken)
    }

}
