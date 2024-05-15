package com.bamyanggang.apimodule.domain.user.application.dto

data class UserInfoResponse(
    val nickName: String,
    val profileImgUrl: String,
    val jobSearchStatus: String?,
    val desiredJob: String?,
    val goal: String?,
    val dream: String?,
)