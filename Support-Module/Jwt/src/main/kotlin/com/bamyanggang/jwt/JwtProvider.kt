package com.bamyanggang.jwt

import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtProvider(
    private val jwtProperties: JwtProperties,
    private val jwtKeyGenerator: JwtKeyGenerator,
    private val jwtRegistry: JwtRegistry
) {

    fun generateAccessToken(user: Claims.UserClaims): String {
        return generateToken(
            user.createPrivateClaims(TokenType.ACCESS_TOKEN),
            jwtProperties.accessTokenExpirationTime
        )
    }

    fun generateRefreshToken(user: Claims.UserClaims): String {
        val refreshToken = generateToken(
            user.createPrivateClaims(TokenType.REFRESH_TOKEN),
            jwtProperties.refreshTokenExpirationTime
        )
        jwtRegistry.upsert(user.userId to refreshToken)
        return refreshToken
    }

    fun generateRegistrationToken(registrationClaims: Claims.RegistrationClaims): String {
        return generateToken(
            registrationClaims.createPrivateClaims(TokenType.REGISTRATION_TOKEN),
            jwtProperties.registrationTokenExpirationTime
        )
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
