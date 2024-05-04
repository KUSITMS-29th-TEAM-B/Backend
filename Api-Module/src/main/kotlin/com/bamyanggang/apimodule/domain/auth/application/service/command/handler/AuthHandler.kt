package com.bamyanggang.apimodule.domain.auth.application.service.command.handler

import com.bamyanggang.domainmodule.domain.auth.enum.SocialLoginProvider

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