package com.bamyanggang.jwt

import com.bamyanggang.cache.CacheManager
import com.bamyanggang.jwt.exception.JwtException
import com.bamyanggang.lock.LockManager
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtProvider(
    private val jwtProperties: JwtProperties,
    private val jwtKeyGenerator: JwtKeyGenerator,
    private val claimsExtractor: ClaimsExtractor,
    private val jwtValidator: JwtValidator,
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

    fun reissueToken(refreshToken: String): Pair<String, String> = LockManager.lockByKey(refreshToken) {
        CacheManager.cacheByKey(refreshToken) {
            if (jwtRegistry.isExists(refreshToken).not()) {
                throw JwtException.TokenNotFoundException()
            }
            jwtValidator.validateToken(refreshToken, TokenType.REFRESH_TOKEN)
            val userClaims: Claims.UserClaims = claimsExtractor.extractClaimsFromToken(
                refreshToken, TokenType.REFRESH_TOKEN,
                JwtConst.USER_CLAIMS, Claims.UserClaims::class.java
            )
            val accessToken = generateAccessToken(userClaims)
            val newRefreshToken = generateRefreshToken(userClaims)

            jwtRegistry.upsert(userClaims.userId to newRefreshToken)

            return@cacheByKey Pair(accessToken, newRefreshToken)
        }
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