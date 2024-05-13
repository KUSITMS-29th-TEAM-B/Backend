package com.bamyanggang.jwt

data class Token(
    val accessToken: String,
    val refreshToken: String
)
