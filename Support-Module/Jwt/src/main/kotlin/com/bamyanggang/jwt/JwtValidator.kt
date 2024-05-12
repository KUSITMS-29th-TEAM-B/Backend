package com.bamyanggang.jwt

import io.jsonwebtoken.*
import io.jsonwebtoken.jackson.io.JacksonDeserializer
import io.jsonwebtoken.security.SignatureException
import org.springframework.stereotype.Component
import com.bamyanggang.jwt.exception.JwtException

@Component
class JwtValidator(
    private val jwtKeyGenerator: JwtKeyGenerator
) {

    fun validateToken(token: String, tokenType: TokenType) {
        val jwtParser = initializeJwtParser(tokenType)
        try {
            jwtParser.parse(token)
        } catch (ex: Exception) {
            when (ex) {
                is MalformedJwtException,
                is SignatureException,
                is IncorrectClaimException,
                is IllegalArgumentException -> throw JwtException.InvalidTokenException()
                is ExpiredJwtException -> throw JwtException.ExpiredTokenException()
                else -> throw ex
            }
        }
    }


    fun initializeJwtParser(tokenType: TokenType): JwtParser {
        return Jwts.parser()
            .json(JacksonDeserializer(PrivateClaims.retrieveClaimsClassType()))
            .verifyWith(jwtKeyGenerator.generateKey())
            .requireIssuer(JwtConst.TOKEN_ISSUER)
            .require(JwtConst.TOKEN_TYPE, tokenType)
            .build()
    }
}