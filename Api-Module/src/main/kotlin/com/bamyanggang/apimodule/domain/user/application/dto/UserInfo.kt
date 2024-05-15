package com.bamyanggang.apimodule.domain.user.application.dto

class UserInfo {

    sealed class Request {
        data class UpdateUserInfo(
            val nickName: String?,
            val profileImgUrl: String?,
            val jobSearchStatus: String?,
            val desiredJob: String?,
            val goal: String?,
            val dream: String?,
        ): Request()

    }

    sealed class Response {
        data class Success(
            val nickName: String,
            val profileImgUrl: String,
            val jobSearchStatus: String?,
            val desiredJob: String?,
            val goal: String?,
            val dream: String?,
        ): Response()
    }
}