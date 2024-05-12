package com.bamyanggang.jwt


import com.bamyanggang.jwt.exception.JwtException
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