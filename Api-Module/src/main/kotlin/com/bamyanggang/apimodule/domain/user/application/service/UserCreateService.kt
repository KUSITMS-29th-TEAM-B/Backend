package com.bamyanggang.apimodule.domain.user.application.service

import com.bamyanggang.apimodule.domain.user.application.dto.Register
import com.bamyanggang.domainmodule.domain.user.aggregate.User
import com.bamyanggang.domainmodule.domain.user.service.TokenAppender
import com.bamyanggang.domainmodule.domain.user.service.UserAppender
import com.bamyanggang.jwt.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserCreateService(
    private val jwtValidator: JwtValidator,
    private val userAppender: UserAppender,
    private val claimsExtractor: ClaimsExtractor,
    private val jwtProvider: JwtProvider,
    private val tokenAppender: TokenAppender
) {

    @Transactional
    fun createUser(request: Register.Request): Register.Response.Success {
        jwtValidator.validateToken(request.registrationToken, TokenType.REGISTRATION_TOKEN)
        val claims = claimsExtractor.extractClaimsFromToken(request.registrationToken, TokenType.REGISTRATION_TOKEN
            , JwtConst.REGISTRATION_CLAIMS, Claims.RegistrationClaims::class.java)

        val socialId = claims.socialId
        val email = claims.email

        val user: User = userAppender.appendUser(
                socialId = socialId,
                profileImgUrl = request.profileImgUrl,
                provider = request.provider,
                email = email,
                nickName = request.nickName,
                jobSearchStatus = request.jobSearchStatus,
                desiredJob = request.desiredJob,
                goal = request.goal,
                dream = request.dream
        )

        val accessToken = jwtProvider.generateAccessToken(Claims.UserClaims(user.id))
        val refreshToken = jwtProvider.generateRefreshToken(Claims.UserClaims(user.id))

        tokenAppender.appendToken(user.id, refreshToken)
        return Register.Response.Success(
            accessToken = accessToken,
            refreshToken = refreshToken
        )

    }

}
