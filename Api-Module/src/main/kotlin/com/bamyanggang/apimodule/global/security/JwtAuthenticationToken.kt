package com.bamyanggang.apimodule.global.security

import org.springframework.security.authentication.AbstractAuthenticationToken

class JwtAuthenticationToken(private val userId: Long) : AbstractAuthenticationToken(null) {

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
