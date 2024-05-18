package com.bamyanggang.apimodule.domain.user.application.service

import com.bamyanggang.apimodule.common.getAuthenticationPrincipal
import com.bamyanggang.apimodule.domain.user.application.dto.UserInfo
import com.bamyanggang.domainmodule.domain.user.service.UserReader
import org.springframework.stereotype.Service

@Service
class UserInfoGetService(
    private val userReader: UserReader
) {
    fun getUserInfo(): UserInfo.Response.Success {
        getAuthenticationPrincipal()
            .let { userReader.readUserById(it) }
            .let {
                return UserInfo.Response.Success(
                    nickName = it.nickName,
                    profileImgUrl = it.profileImgUrl,
                    provider = it.provider,
                    email = it.email,
                    jobSearchStatus = it.jobSearchStatus,
                    desiredJob = it.desiredJob,
                    goal = it.goal,
                    dream = it.dream
                )
            }
    }
}
