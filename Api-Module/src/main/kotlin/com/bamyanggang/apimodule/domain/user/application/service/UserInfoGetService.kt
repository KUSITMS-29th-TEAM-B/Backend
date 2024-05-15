package com.bamyanggang.apimodule.domain.user.application.service

import com.bamyanggang.apimodule.common.UserUtils
import com.bamyanggang.apimodule.domain.user.application.dto.UserInfoResponse
import org.springframework.stereotype.Service

@Service
class UserInfoGetService(
    private val userUtils: UserUtils
) {

    fun getUserInfo(): UserInfoResponse {
        val user = userUtils.getAccessUser()
        return UserInfoResponse(
            nickName = user.nickName,
            profileImgUrl = user.profileImgUrl,
            jobSearchStatus = user.jobSearchStatus,
            desiredJob = user.desiredJob,
            goal = user.goal,
            dream = user.dream
        )
    }
}