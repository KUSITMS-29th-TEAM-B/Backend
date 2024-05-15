package com.bamyanggang.apimodule.domain.user.application.dto

class Logout {

    data class Request(
        val refreshToken: String
    )

}