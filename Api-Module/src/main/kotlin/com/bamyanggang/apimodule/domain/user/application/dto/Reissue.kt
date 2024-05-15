package com.bamyanggang.apimodule.domain.user.application.dto

class Reissue {

    data class Request(
        val refreshToken: String
    )

    data class Response(
        val accessToken: String,
        val refreshToken: String
    )
}