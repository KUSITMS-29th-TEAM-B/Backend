package com.bamyanggang.domainmodule.domain.auth.jwt

import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import java.util.*

/**
 * Todo : 토큰 캐싱 처리 추가
 */
@Component
class JwtProvider(
    private val jwtProperties: JwtProperties,
    private val jwtKeyGenerator: JwtKeyGenerator,
    private val claimsExtractor: ClaimsExtractor,
    private val jwtValidator: JwtValidator,
) {

    fun generateAccessToken(user: Claims.UserClaims): String {
        return generateToken(
            user.createPrivateClaims(TokenType.ACCESS_TOKEN),
            jwtProperties.accessTokenExpirationTime
        )
    }

    fun generateRefreshToken(user: Claims.UserClaims): String {
        return generateToken(
            user.createPrivateClaims(TokenType.REFRESH_TOKEN),
            jwtProperties.refreshTokenExpirationTime
        )
    }

    fun generateRegistrationToken(registrationClaims: Claims.RegistrationClaims): String {
        return generateToken(
            registrationClaims.createPrivateClaims(TokenType.REGISTRATION_TOKEN),
            jwtProperties.registrationTokenExpirationTime
        )
    }

    fun reissueToken(refreshToken: String) : Token {
        jwtValidator.validateToken(refreshToken, TokenType.REFRESH_TOKEN)
        val userClaims: Claims.UserClaims = claimsExtractor.extractClaimsFromToken(
            refreshToken, TokenType.REFRESH_TOKEN,
            JwtConst.USER_CLAIMS, Claims.UserClaims::class.java
        )
        val accessToken = generateAccessToken(userClaims)
        val newRefreshToken = generateRefreshToken(userClaims)
        return Token(accessToken, newRefreshToken)
    }

    private fun generateToken(claims: PrivateClaims, expireTime: Long): String {
        val now = Date()
        return Jwts.builder()
            .issuer(JwtConst.TOKEN_ISSUER)
            .claims(claims.convertToClaims())
            .issuedAt(now)
            .expiration(Date(now.time + expireTime))
            .signWith(jwtKeyGenerator.generateKey())
            .compact()
    }
}