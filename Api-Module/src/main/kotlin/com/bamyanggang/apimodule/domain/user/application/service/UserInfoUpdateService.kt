package com.bamyanggang.apimodule.domain.user.application.service

import com.bamyanggang.apimodule.common.getAuthenticationPrincipal
import com.bamyanggang.apimodule.domain.user.application.dto.UserInfo
import com.bamyanggang.domainmodule.domain.user.service.UserModifier
import org.springframework.stereotype.Service

@Service
class UserInfoUpdateService(
    private val userModifier: UserModifier
) {

    fun updateUserInfo(request: UserInfo.Request.UpdateUserInfo) {
        getAuthenticationPrincipal()
            .also { userModifier.modifyUserInfo(
                it,
                nickName = request.nickName,
                profileImgUrl = request.profileImgUrl,
                jobSearchStatus = request.jobSearchStatus,
                desiredJob = request.desiredJob,
                goal = request.goal,
                dream = request.dream,
            ) }
    }


}