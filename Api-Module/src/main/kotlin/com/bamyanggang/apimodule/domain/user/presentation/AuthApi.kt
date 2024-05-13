package com.bamyanggang.apimodule.domain.user.presentation

object AuthApi {

    const val BASE_URL = "/api/auth"
    const val SOCIAL_LOGIN = "$BASE_URL/login/{provider}"
    const val LOGOUT = "$BASE_URL/logout"
    const val REISSUE = "$BASE_URL/reissue"

}