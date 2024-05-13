package com.bamyanggang.apimodule.domain.user.application.dto

import com.bamyanggang.domainmodule.domain.user.enums.DesiredJob
import com.bamyanggang.domainmodule.domain.user.enums.JobSearchStatus
import com.bamyanggang.domainmodule.domain.user.enums.SocialLoginProvider

class Register {

    data class Request(
        val registrationToken: String,
        val profileImgUrl: String,
        val provider: SocialLoginProvider,
        val nickName: String,
        val jobSearchStatus: JobSearchStatus?,
        val desiredJob: DesiredJob?,
        val goal: String?,
        val dream: String?
    )

    sealed class Response{

        data class Success(
            val accessToken: String,
            val refreshToken: String
        ): Response()

    }
}