package com.bamyanggang.supportmodule.jwt


import com.bamyanggang.supportmodule.jwt.exception.JwtException
import org.springframework.stereotype.Component

@Component
class TokenExtractor {
    object Constants {
        const val PREFIX = "Bearer "
    }

    fun extractValue(token: String): String {
        if(token.startsWith(Constants.PREFIX)) {
            return token.substring(Constants.PREFIX.length)
        }
        throw JwtException.InvalidTokenException()
    }
}