package com.bamyanggang.apimodule.domain.user.application.service.handler

import com.bamyanggang.domainmodule.domain.user.enums.SocialLoginProvider

interface AuthHandler {

    val provider: SocialLoginProvider
    fun handle(request: Request): Response

    data class Request(
        val accessToken: String
    )

    data class Response(
        val socialId: String,
        val nickName: String,
        val email: String
    )
}
