package com.bamyanggang.supportmodule.jwt

data class Token(
    val accessToken: String,
    val refreshToken: String
)
