package com.bamyanggang.supportmodule.jwt

import org.springframework.stereotype.Component

@Component
class ClaimsExtractor(private val jwtValidator: JwtValidator) : ExtractClaims {
    override fun <T : Claims> extractClaimsFromToken(
        token: String,
        tokenType: TokenType,
        claimsKey: String,
        classType: Class<T>
    ): T {
        return jwtValidator.initializeJwtParser(tokenType)
            .parseSignedClaims(token)
            .payload
            .get(claimsKey, classType)
    }
}

interface ExtractClaims {
    fun <T : Claims> extractClaimsFromToken(token: String, tokenType: TokenType, claimsKey: String, classType: Class<T>): T
}
