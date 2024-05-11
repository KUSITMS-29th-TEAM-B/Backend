package com.bamyanggang.apimodule.domain.user.application.service

import com.bamyanggang.supportmodule.jwt.*
import org.springframework.stereotype.Service
import java.util.*

@Service
class TokenService(
    private val jwtValidator: JwtValidator,
    private val claimsExtractor: ClaimsExtractor
) {
    fun resolveAccessToken(accessToken: String): UUID {
        return jwtValidator.validateToken(accessToken, TokenType.ACCESS_TOKEN)
            .let {
                claimsExtractor.extractClaimsFromToken(
                    accessToken,
                    TokenType.ACCESS_TOKEN,
                    JwtConst.USER_CLAIMS,
                    Claims.UserClaims::class.java
                ).userId
            }
    }
}