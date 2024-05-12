package com.bamyanggang.apimodule.domain.user.application.service.command.handler

import com.bamyanggang.domainmodule.domain.user.enums.SocialLoginProvider

interface AuthHandler {

    val provider: SocialLoginProvider

    fun isAccessible(provider: SocialLoginProvider): Boolean {
        return false
    }

    fun handle(request: Request): Response

    data class Request(
        val accessToken: String
    )

    data class Response(
        val socialId: String,
        val nickName: String
    )
}