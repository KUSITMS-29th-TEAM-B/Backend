package com.bamyanggang.domainmodule.domain.auth.jwt

data class Token(
    val accessToken: String,
    val refreshToken: String
)
