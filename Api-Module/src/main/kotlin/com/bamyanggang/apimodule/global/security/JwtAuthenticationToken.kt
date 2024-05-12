package com.bamyanggang.apimodule.global.security

import org.springframework.security.authentication.AbstractAuthenticationToken
import java.util.UUID

class JwtAuthenticationToken(private val userId: UUID) : AbstractAuthenticationToken(null) {

    init {
        isAuthenticated = true
    }

    override fun getCredentials(): Any? {
        return null
    }

    override fun getPrincipal(): Any? {
        return userId
    }
}
